//
//  String+Letters.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 06/07/21.
//

import Foundation

extension String {
    var letters: String {
        return components(separatedBy: CharacterSet.letters.inverted).joined()
    }
}
