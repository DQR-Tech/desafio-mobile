//
//  String+Locale.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 06/07/21.
//

import UIKit

extension String {
    func locale() -> Locale {
        var locale = Locale.current
        if (locale.currencyCode != self) {
            let identifier = NSLocale.localeIdentifier(fromComponents: [NSLocale.Key.currencyCode.rawValue: self])
            locale = NSLocale(localeIdentifier: identifier) as Locale
        }
        return locale;
    }
}
