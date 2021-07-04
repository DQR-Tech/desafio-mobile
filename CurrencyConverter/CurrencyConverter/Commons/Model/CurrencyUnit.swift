//
//  CurrencyUnit.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 03/07/21.
//

import Foundation

class CurrencyUnit {
    let name: String
    let id: String
    let rate: Double
    
    init(name: String, id: String, rate: Double) {
        self.name = name
        self.id = id
        self.rate = rate
    }
}
