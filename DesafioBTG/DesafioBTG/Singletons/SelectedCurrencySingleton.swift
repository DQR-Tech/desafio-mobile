//
//  SelectedCurrencySingleton.swift
//  DesafioBTG
//
//  Created by Euclides Medeiros on 08/07/21.
//

import Foundation

struct SelectedCurrencySingleton {
    static var selectedCurrency: selectedCurrency?
    
}

enum selectedCurrency {
    case ofCurrency
    case toCurrency
}
