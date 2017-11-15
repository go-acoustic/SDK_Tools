//
//  TLConnectData.m
//  TL Connect
//
//  Created by Geoff Heeren on 1/27/14.
/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/

#import "TLConnectData.h"

@implementation TLConnectData
#pragma mark - Data
+(NSArray*)getIndustries
{
	return @[@"Select One",@"Creative Agency / Design", @"Energy and Utilities", @"Financial Services", @"Government", @"HighTech / Software"
			 , @"Insurance", @"Manufacturing", @"Not for Profit", @"Pharma / Healthcare", @"Retail", @"Telecommunications",
			 @"Transportation and Logistics", @"Travel and Hospitality", @"Other"];
}
+(NSArray*)getRoles
{
	return @[@"Select One",@"Marketing", @"Online Marketing", @"e-Commerce", @"eBusiness", @"Mobile", @"Business Development",
			 @"Customer Experience", @"User Experience", @"Site Optimization", @"Customer Service", @"Web Operations",
			 @"Web Analyst / Analytics", @"Project Manager", @"Quality Assurance", @"Sales", @"Development / Developer",
			 @"Information Technology", @"Consulting", @"Student / Professor", @"Other", @"Partner"];
}
+(NSArray*)getTitles
{
	return @[@"Select One",@"Coordinator", @"Specialist", @"Manager", @"Senior Manager", @"Director", @"Senior Director", @"Vice President",
			 @"Senior Vice President", @"President", @"Chief Officer", @"Consultant", @"Student", @"Other"];
}
+(NSArray*)getCountries
{
	return @[@"Select One",@"Afghanistan", @"Albania", @"Algeria", @"American Samoa", @"Andorra", @"Angola", @"Anguilla", @"Antigua & Barbuda",
			 @"Argentina", @"Armenia", @"Aruba", @"Australia", @"Austria", @"Azerbaijan", @"Bahamas", @"Bahrain", @"Bangladesh",
			 @"Barbados", @"Belarus", @"Belgium", @"Belize", @"Benin", @"Bermuda", @"Bhutan", @"Bolivia", @"Bonaire",
			 @"Bosnia & Herzegovina", @"Botswana", @"Brazil", @"British Indian Ocean Ter", @"Brunei", @"Bulgaria", @"Burkina Faso",
			 @"Burundi", @"Cambodia", @"Cameroon", @"Canada", @"Canary Islands", @"Cape Verde", @"Cayman Islands", @"Central African Republic",
			 @"Chad", @"Channel Islands", @"Chile", @"China", @"Christmas Island", @"Cocos Island", @"Colombia", @"Comoros", @"Congo",
			 @"Cook Islands", @"Costa Rica", @"Cote D'Ivoire", @"Croatia", @"Cuba", @"Curacao", @"Cyprus", @"Czech Republic", @"Denmark",
			 @"Djibouti", @"Dominica", @"Dominican Republic", @"East Timor", @"Ecuador", @"Egypt", @"El Salvador", @"Equatorial Guinea",
			 @"Eritrea", @"Estonia", @"Ethiopia", @"Falkland Islands", @"Faroe Islands", @"Fiji", @"Finland", @"France", @"French Guiana",
			 @"French Polynesia", @"French Southern Ter", @"Gabon", @"Gambia", @"Georgia", @"Germany", @"Ghana", @"Gibraltar", @"Great Britain",
			 @"Greece", @"Greenland", @"Grenada", @"Guadeloupe", @"Guam", @"Guatemala", @"Guinea",
			 @"Guyana", @"Haiti", @"Hawaii", @"Honduras", @"Hong Kong", @"Hungary", @"Iceland", @"India", @"Indonesia", @"Iran", @"Iraq",
			 @"Ireland", @"Isle of Man", @"Israel", @"Italy", @"Jamaica", @"Japan", @"Jordan", @"Kazakhstan", @"Kenya", @"Kiribati", @"Korea North",
			 @"Korea South", @"Kuwait", @"Kyrgyzstan", @"Laos", @"Latvia", @"Lebanon", @"Lesotho", @"Liberia", @"Libya", @"Liechtenstein", @"Lithuania",
			 @"Luxembourg", @"Macau", @"Macedonia", @"Madagascar", @"Malaysia", @"Malawi", @"Maldives", @"Mali", @"Malta", @"Marshall Islands", @"Martinique",
			 @"Mauritania", @"Mauritius", @"Mayotte", @"Mexico", @"Midway Islands", @"Moldova", @"Monaco", @"Mongolia", @"Montserrat",
			 @"Morocco", @"Mozambique", @"Myanmar", @"Nambia", @"Nauru", @"Nepal", @"Netherland Antilles", @"Netherlands", @"Nevis",
			 @"New Caledonia", @"New Zealand", @"Nicaragua", @"Niger", @"Nigeria", @"Niue", @"Norfolk Island", @"Norway", @"Oman",
			 @"Pakistan", @"Palau Island", @"Palestine", @"Panama", @"Papua New Guinea", @"Paraguay", @"Peru", @"Philippines",
			 @"Pitcairn Island", @"Poland", @"Portugal", @"Puerto Rico", @"Qatar", @"Reunion", @"Romania", @"Russia", @"Rwanda",
			 @"St Barthelemy", @"St Eustatius", @"St Helena", @"St Kitts-Nevis", @"St Lucia", @"St Maarten", @"St Pierre & Miquelon",
			 @"St Vincent & Grenadines", @"Saipan", @"Samoa", @"Samoa American", @"San Marino", @"Sao Tome & Principe", @"Saudi Arabia",
			 @"Senegal", @"Seychelles", @"Serbia & Montenegro", @"Sierra Leone", @"Singapore", @"Slovakia", @"Slovenia", @"Solomon Islands",
			 @"Somalia", @"South Africa", @"Spain", @"Sri Lanka", @"Sudan", @"Suriname", @"Swaziland", @"Sweden", @"Switzerland",
			 @"Syria", @"Tahiti", @"Taiwan", @"Tajikistan", @"Tanzania", @"Thailand", @"Togo", @"Tokelau", @"Tonga", @"Trinidad & Tobago",
			 @"Tunisia", @"Turkey", @"Turkmenistan", @"Turks & Caicos Is", @"Tuvalu", @"Uganda", @"Ukraine", @"United Arab Emirates",
			 @"United Kingdom", @"United States", @"Uruguay", @"Uzbekistan", @"Vanuatu", @"Vatican City State", @"Venezuela",
			 @"Vietnam", @"Virgin Islands (Brit)", @"Virgin Islands (USA)", @"Wake Island", @"Wallis & Futana Is", @"Yemen",
			 @"Zaire", @"Zambia", @"Zimbabwe"];
}
+(NSArray*)getStates
{
	return @[@"Select One",@"[Not Applicable]", @"Alabama", @"Alaska", @"Arizona", @"Arkansas", @"California",
			 @"Colorado", @"Connecticut", @"Delaware", @"District Of Columbia", @"Florida", @"Georgia", @"Hawaii", @"Idaho",
			 @"Illinois", @"Indiana", @"Iowa", @"Kansas", @"Kentucky", @"Louisiana", @"Maine", @"Maryland",
			 @"Massachusetts", @"Michigan", @"Minnesota", @"Mississippi", @"Missouri", @"Montana", @"Nebraska", @"Nevada",
			 @"New Hampshire", @"New Jersey", @"New Mexico", @"New York", @"North Carolina",
			 @"North Dakota", @"Ohio", @"Oklahoma", @"Oregon", @"Pennsylvania",
			 @"Rhode Island", @"South Carolina", @"South Dakota", @"Tennessee", @"Texas", @"Utah",
			 @"Vermont", @"Virginia", @"Washington", @"West Virginia", @"Wisconsin", @"Wyoming"];
}
+(NSArray*)getProvinces
{
	return @[@"Select One",@"[Not Applicable]", @"Alberta", @"British Columbia", @"Manitoba",@"New Brunswick",
			 @"Newfoundland", @"Nova Scotia", @"Prince Edward Island", @"Quebec", @"Saskatchewan"];
}
@end
