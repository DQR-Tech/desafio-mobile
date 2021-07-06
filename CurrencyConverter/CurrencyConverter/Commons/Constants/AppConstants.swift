//
//  AppConstants.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import Foundation

struct Scenes {
    struct Conversion {
        static let title = NSLocalizedString("scenes.conversion.title", comment: "")
    }
    struct Selection {
        static let title = NSLocalizedString("scenes.selection.title", comment: "")
    }
}

struct Currency {
    static var shared: Currency = Currency()
    
    static var conversionRates: [String:Double] = [:]
    
    static var availableUnits: [String:String] = [:]
    
    static var latestUpdateTimestamp: Int = 1481721300
}
