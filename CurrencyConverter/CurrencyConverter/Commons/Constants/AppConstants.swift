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
    static let conversionRates = [
        "USD": 1,
        "BRL": 5.04,
        "AUD": 1.340245,
        "EUR": 0.844365,
        "CAD": 1.243835,
        "CHF": 0.92613
    ]
    static let availableUnits = [
        "USD": "Dollar",
        "BRL": "Brazilian Real",
        "AUD": "Australian Dollar",
        "EUR": "Euro",
        "CAD": "Canadian Dollar",
        "CHF": "Swiss Franc"
    ]
}
