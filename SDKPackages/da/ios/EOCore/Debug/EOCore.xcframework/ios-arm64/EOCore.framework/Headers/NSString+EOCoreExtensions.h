//
//  NSString+EOCoreExtensions.h
//  EOCore
//
//  Created by ohernandezltkmac on 3/7/19.
// Copyright (C) 2019 Acoustic, L.P. All rights reserved.
//
// NOTICE: This file contains material that is confidential and proprietary to
// Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
// industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
// Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
// prohibited.
//
#import <Foundation/Foundation.h>

@interface NSString (EOCoreExtensions)

/*!
 * @abstract Get the class name to a formatted NSString removing any swift additions.
 * @discussion The class name to a formatted NSString removing any swift additions..
 * @return The class name to a formatted NSString removing any swift additions.
 */
-(NSString *)tealeafClass;
@end
