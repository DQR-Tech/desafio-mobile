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
    static var conversionRates = [
        "USD": 1,
        "BRL": 5.04,
    ]
    static var availableUnits: [String:String] = [ :
    ]

}
