//
//  Decimal+ConvertApp.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 22/04/21.
//

import Foundation

extension Decimal {
    var currency: String { Formatter.currency.string(for: self) ?? "" }
}
