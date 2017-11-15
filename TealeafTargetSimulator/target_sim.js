//
//  OCO Source Materials
//
//  
//  5725-K23
//
//  (c) Copyright IBM Corp. 2014
//
//  The source code for this program is not published or otherwise divested
//  of its trade secrets, irrespective of what has been deposited with the
//  U.S. Copyright Office.
//
//


//Tealeaf Target Simulator
//
//This application accepts incomming POSTs from the mobile SDKs and
//saves any embedded (base64) images to the filesystem


var http = require('http'),
	fs = require('fs'),
	zlib = require('zlib'),
	path = require('path'),
	crypto = require('crypto'),
	os = require('os'),
	port = isNaN(process.argv[2]) ? 9000 : process.argv[2],
	IMAGES_PATH = 'images',
	VERSION = '1.07';

console.log(GetVersion());
console.log(Strings('StartupMessage'));

//create the server, interior function is callback when request is received
var server = http.createServer(function (req, res) {
	//we will accumulate chunks of the request into requestBody with every 'data' event until we get 'end' event
	var requestBody = null;

	//data event - received new chunk of the request, append to requestBody
	req.on('data',function(chunk){
		requestBody = requestBody == null ? chunk : Buffer.concat([requestBody, chunk]);
	});

	//end event - request is complete in the requestBody - now process it
	req.on('end',function(){
		var responseBody;
		//if request was a POST then unzip (if necessary), then Process and prepare appropriate response
		if (requestBody && req.method == "POST") {
			if (req.headers['content-encoding'] == 'gzip') {
				zlib.unzip(requestBody, function(err, requestBodyExpanded){
					if (err) {
						console.log("GZIP ERR: " + err);
					}
					else {
						ProcessMessage(requestBodyExpanded);
					}
				});
			}
			else {
				ProcessMessage(requestBody);
			}

			responseBody = '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">\n'+
							'<html>\n<head><title>Tealeaf Target</title></head>\n'+
							'<body>\nTealeaf Target 1.0\n<br/>\n<hr/>\n' +
							'Read ' + requestBody.length + ' bytes in 0.00ms\n'+
							'</body>\n</html>\n';
		}
		else {
			//if request was a GET then just respond with app name and version
			responseBody = 'Tealeaf Target Simulator v' + VERSION;
		}

		//return the response, this request/response cycle is now complete
		res.setHeader("Content-Length", responseBody.length);
		res.setHeader("Content-Type", "text/plain; charset=utf-8");
		res.write(responseBody);
		res.end();		
	})
});

//error event - occurs if the server encounters and error, such as port being occupied
server.on('error', function(e) {
	if (e.code == 'EADDRINUSE') {
		console.log(e);
		console.log(Strings('IsAppRunning'));
		console.log(Strings('IsPortInUse',port));
	}
	else {
		console.log(e);
	}
});

//listening event - occurs when the server successfully starts listening
server.on('listening', function() {
	console.log(Strings('Listening'));
	GetUrlList().forEach(function(url){
		console.log(url);
	});
});

//when we exit, warn user to revert to production settings
process.on('SIGINT',function(){
	console.log("");
	console.log(Strings("ProductionWarning","GetImageDataOnScreenLayout","NO/false"));
	console.log("");
	process.exit();
})

//start the server on the specified port
server.listen(port);

//if we receive a POST, the request body is processed here
function ProcessMessage(message) {
	console.log(Strings('PostReceivedProcessing'));

	//we expect a JSON message, fail if we cannot parse the JSON
	try {
		var messageObj = JSON.parse(message);
	}
	catch(ex) {
		console.log('POST != JSON');
		return;
	}

	//create a directory for images and call GetImages to save all images in the JSON
	var foundList = {};
	CreateImagesDir();
	GetImages(messageObj, foundList);
	var foundCount = Object.keys(foundList).length;
	if (foundCount == 0) {
		console.log(Strings('NoImagesFound'));
	}
	else {
		console.log(Strings('ReportImagesFound', foundCount, IMAGES_PATH));
	}
}

//GetImages will recursively call itself, searching through the JSON tree for images
//keeping track of the images found, and writing those images to disk
function GetImages(value, foundList) {
	//in this case, 'object' means object or array
	if (typeof value != 'object')
		return;

	if (value['base64Image']) {
		//if the object has a base64Image member, then extract it
		var imageData = value['base64Image'],
			imageName = value['value'] || GetImageChecksum(imageData),
			imageExt = value['mimeExtension'] || 'png';

		if (foundList[imageName]) {
			console.log(Strings('ImageResultDuplicateHash') + ' ' + imageName);
		}
		else {
			foundList[imageName] = true;
			SaveImage(imageData, imageName, imageExt);
		}		
	}
	else {
		//if object does not have a base64Image member, then search its children
		for(var key in value) {
			GetImages(value[key], foundList);
		}
	}		
}

//once we find base64 data, use SaveImage function to create a file with that image data
function SaveImage(imageData, imageName, imageExt) {
	var fileName = IMAGES_PATH + path.sep + imageName+"."+imageExt,
		options = {
			encoding:'base64',
			flag:'wx'
		};

	//writeFile occurs asynchronously, interior callback reports result
	fs.writeFile(fileName, imageData, options, function(err){
		if (!err) {
			console.log(Strings('ImageResultSaved') + ' ' + fileName);
		}
		else if (err.code && err.code == "EEXIST") {
			console.log(Strings('ImageResultFileExists') + ' ' + fileName);
		}
		else {
			console.log(err);
		}
	});
}

//get checksum of base64 image data
function GetImageChecksum(base64ImageData) {
	var hash = crypto.createHash('md5');
	hash.update(base64ImageData,'base64');
	return hash.digest('hex').toUpperCase();
}

//Synchronous creation of images dir
function CreateImagesDir() {
	try {
		fs.mkdirSync(IMAGES_PATH);
	}
	catch (ex) {
		if (ex.code != 'EEXIST') {
			console.log(ex);
			process.exit(1);
		}
	}	
}

//Return version of this app and of node
function GetVersion() {
	return ("Tealeaf Target Simulator v" + VERSION + "\n" +
					"Node " + process.version +
					(process.features.ibmras ? " IBM" : ""));
}

//Build list of URLs we're listening on
function GetUrlList()
{
	var urlListExt = [];
	var urlListInt = [];
	var nics = os.networkInterfaces();
	for (var nicName in nics) {
		var nic = nics[nicName];
		for(var i=0; i<nic.length; i++) {
			var addr = nic[i];
			if (addr.family == "IPv4") {
				var url = '\thttp://' + addr.address + ':' + port + '/TealeafTarget';
				if (addr.internal)
					urlListInt.push(url + ' (Xcode)');
				else
					urlListExt.push(url);
			}			
		}
	}
	//show external URLs first, loopback last
	return urlListExt.concat(urlListInt);
}

//Strings class for translation
function Strings(key, val0, val1){

	var stringLib = {
		"en": {
			"ImageResultDuplicateHash": "Duplicate Hash:",
			"ImageResultFileExists": "File Exists:",
			"ImageResultSaved": "Saved File:",
			"IsAppRunning": "Is the application already running?",
			"IsPortInUse": "Is port {0} already in use?",
			"Listening": "Listening...",
			"NoImagesFound": "The JSON was processed, but no images were found.  Check the 'GetImageDataOnScreenLayout' setting in the SDK.",
			"PostReceivedProcessing": "POST received.  Processing...",
			"ProductionWarning": "NOTE: For production set {0} to {1}",
			"ReportImagesFound": "{0} distinct images found or written to {1}",
			"StartupMessage": "Please consult documentation on SDK settings for image harvesting."
		},
		"de": {
			"ImageResultDuplicateHash": "Doppelter Hashwert:",
			"ImageResultFileExists": "Datei ist vorhanden:",
			"ImageResultSaved": "Gespeicherte Datei:",
			"IsAppRunning": "Wird die Anwendung bereits ausgeführt?",
			"IsPortInUse": "Ist Port {0} bereits belegt?",
			"Listening": "Empfangsbereit...",
			"NoImagesFound": "JSON wurde verarbeitet, es wurden jedoch keine Abbildungen gefunden.  Prüfen Sie die Einstellung 'GetImageDataOnScreenLayout' im SDK.",
			"PostReceivedProcessing": "POST erhalten.  Verarbeitung läuft...",
			"ProductionWarning": "HINWEIS: Legen Sie für die Produktion {0} auf {1} fest",
			"ReportImagesFound": "{0} unterschiedliche Abbildungen gefunden oder in {1} geschrieben",
			"StartupMessage": "Ziehen Sie zum Thema Abbildungsharvesting die Dokumentation zu den SDK-Einstellungen zu Rate."
		},
		"es": {
			"ImageResultDuplicateHash": "Hash duplicado:",
			"ImageResultFileExists": "El archivo existe:",
			"ImageResultSaved": "Archivo guardado:",
			"IsAppRunning": "¿Está la aplicación ya en ejecución?",
			"IsPortInUse": "¿El puerto {0} está ya en uso?",
			"Listening": "Escuchando...",
			"NoImagesFound": "JSON se ha procesado pero no se han encontrado imágenes.  Consulte el valor 'GetImageDataOnScreenLayout' en SDK.",
			"PostReceivedProcessing": "POST recibido.  Procesando...",
			"ProductionWarning": "NOTA: para la producción establezca {0} en {1}",
			"ReportImagesFound": "{0} imágenes encontradas o escritas en {1}",
			"StartupMessage": "Consulte la documentación sobre los valores de SDK para la recopilación de imágenes."
		},
		"fr": {
			"ImageResultDuplicateHash": "Hachage en double :",
			"ImageResultFileExists": "Fichier existant :",
			"ImageResultSaved": "Fichier sauvegardé :",
			"IsAppRunning": "L'application est-elle déjà en cours d'exécution ?",
			"IsPortInUse": "Le port {0} est-il déjà en cours d'utilisation ?",
			"Listening": "En mode écoute...",
			"NoImagesFound": "JSON traité, mais aucune image n'a été trouvée. Vérifiez le paramètre 'GetImageDataOnScreenLayout' dans le SDK.",
			"PostReceivedProcessing": "POST reçu. Traitement en cours...",
			"ProductionWarning": "REMARQUE : pour l'ensemble de production {0} à {1}",
			"ReportImagesFound": "{0} images distinctes trouvées ou écrites dans {1}",
			"StartupMessage": "Veuillez consulter la documentation sur les paramètres SDK pour la collecte d'images."
		},
		"it": {
			"ImageResultDuplicateHash": "Hash duplicato:",
			"ImageResultFileExists": "Il file esiste:",
			"ImageResultSaved": "File salvato:",
			"IsAppRunning": "L'applicazione è già in esecuzione?",
			"IsPortInUse": "La porta {0} è già in uso?",
			"Listening": "In ascolto...",
			"NoImagesFound": "Il JSON è stato processato ma non è stata trovata alcuna immagine.  Controllare l'impostazione 'GetImageDataOnScreenLayout' nel SDK.",
			"PostReceivedProcessing": "POST ricevuto.  In processo...",
			"ProductionWarning": "NOTA: per il set di produzione da {0} a {1}",
			"ReportImagesFound": "{0} immagini distinte trovate o scritte in {1}",
			"StartupMessage": "Consultare la documentazione sulle impostazioni SDK per la raccolta delle immagini."
		},
		"ja": {
			"ImageResultDuplicateHash": "重複ハッシュ:",
			"ImageResultFileExists": "ファイルが存在します:",
			"ImageResultSaved": "保存済みファイル:",
			"IsAppRunning": "アプリケーションは既に実行中ですか?",
			"IsPortInUse": "ポート {0} は既に使用中ですか?",
			"Listening": "Listen しています...",
			"NoImagesFound": "JSON が処理されましたが、イメージは検出されませんでした。SDK の「GetImageDataOnScreenLayout」設定を確認してください。",
			"PostReceivedProcessing": "POST を受け取りました。処理しています...",
			"ProductionWarning": "注: 実稼働の場合は {0} を {1} に設定します",
			"ReportImagesFound": "{0} 個の異なるイメージが検出または {1} に書き込まれました",
			"StartupMessage": "イメージ収集については、SDK 設定に関する資料を参照してください。"
		},
		"ko": {
			"ImageResultDuplicateHash": "중복 해시:",
			"ImageResultFileExists": "파일이 존재함:",
			"ImageResultSaved": "저장된 파일:",
			"IsAppRunning": "애플리케이션 이미 실행 중입니까?",
			"IsPortInUse": "포트 {0}이(가) 이미 사용 중입니까?",
			"Listening": "청취 중...",
			"NoImagesFound": "JSON 처리되었지만, 이미지를 찾을 수 없습니다. SDK에서 'GetImageDataOnScreenLayout' 설정을 확인하십시오.",
			"PostReceivedProcessing": "POST 수신됨.  처리 중...",
			"ProductionWarning": "참고: 제품에 대해서 {0}을(를) {1}(으)로 설정",
			"ReportImagesFound": "{0}개의 뚜렷한 이미지를 발견되었거나 {1}에 쓰여졌음",
			"StartupMessage": "이미지 수확에 대해서는 SDK 설정 문서를 참조하십시오."
		},
		"pt-br": {
			"ImageResultDuplicateHash": "Hash Duplicado:",
			"ImageResultFileExists": "O arquivo existe:",
			"ImageResultSaved": "Arquivo salvo:",
			"IsAppRunning": "O aplicativo já está em execução?",
			"IsPortInUse": "A porta {0} já está em uso?",
			"Listening": "Recebendo...",
			"NoImagesFound": "O JSON foi processado, mas nenhuma imagem foi localizada.  Verifique a configuração 'GetImageDataOnScreenLayout' no SDK.",
			"PostReceivedProcessing": "POST recebido.  Processando...",
			"ProductionWarning": "OBSERVAÇÃO: Para a produção, configure {0} para {1}",
			"ReportImagesFound": "{0} imagens distintas localizadas ou gravadas em {1}",
			"StartupMessage": "Consulte a documentação nas configurações do SDK para a coleta de imagens."
		},
		"ru": {
			"ImageResultDuplicateHash": "Дублировать хеш:",
			"ImageResultFileExists": "Файл существует:",
			"ImageResultSaved": "Сохраненный файл:",
			"IsAppRunning": "Работает ли приложение?",
			"IsPortInUse": "Используется ли порт {0}?",
			"Listening": "Ожидает...",
			"NoImagesFound": "JSON обработан, но ни одного изображения не найдено. Проверьте параметр 'GetImageDataOnScreenLayout' в SDK.",
			"PostReceivedProcessing": "POST получено. Обработка...",
			"ProductionWarning": "ПРИМЕЧАНИЕ: Для производства задайте для {0} значение {1}",
			"ReportImagesFound": "{0} разных изображений найдено или записано в {1}",
			"StartupMessage": "Информацию о параметрах SDK для сбора изображений смотрите в документации."
		},
		"zh-tw": {
			"ImageResultDuplicateHash": "複製雜湊：",
			"ImageResultFileExists": "檔案已存在：",
			"ImageResultSaved": "已儲存的檔案：",
			"IsAppRunning": "應用程式已在執行中嗎？",
			"IsPortInUse": "埠 {0} 已在使用中嗎？",
			"Listening": "接聽中...",
			"NoImagesFound": "已對 JSON 進行處理，但找不到任何影像。請檢查 SDK 中的 'GetImageDataOnScreenLayout' 設定。",
			"PostReceivedProcessing": "POST 已接收。處理中...",
			"ProductionWarning": "附註：針對正式作業，將 {0} 設定為 {1}",
			"ReportImagesFound": "找到 {0} 個特殊影像或寫入至 {1}",
			"StartupMessage": "請參閱 SDK 設定中的文件以進行影像搜集。"
		},
		"zh": {
			"ImageResultDuplicateHash": "重复散列：",
			"ImageResultFileExists": "文件已存在：",
			"ImageResultSaved": "已保存的文件：",
			"IsAppRunning": "应用程序已在运行吗？",
			"IsPortInUse": "端口 {0} 已在使用吗？",
			"Listening": "正在侦听...",
			"NoImagesFound": "已对 JSON 进行处理，但找不到任何图像。请检查 SDK 中的“GetImageDataOnScreenLayout”设置。",
			"PostReceivedProcessing": "已收到 POST。正在处理...",
			"ProductionWarning": "注：针对生产，将 {0} 设置为 {1}",
			"ReportImagesFound": "找到 {0} 个独特图像或写入至 {1}",
			"StartupMessage": "请查阅 SDK 设置中的文档以进行图像采集。"
		}
	};

	var lang = (process.env.lang || process.env.LANG || 'en').replace('_','-').toLowerCase(),
		lang5 = lang.substr(0,5),
		lang2 = lang.substr(0,2),
		stringMap = stringLib[lang5] || stringLib[lang2] || {};

	//first invocation reassigns itself after setting stringMap into a closure
	Strings = function(key, val0, val1) {
		var value = stringMap[key];
		if (!value)
			return '--== ' + key + ' ==--';

		if (val0 != null)
			value = value.replace('{0}',val0);

		if (val1 != null)
			value = value.replace('{1}',val1);

		return value;
	};

	//first invocation calls the new invocation
	return Strings(key, val0, val1);
};

