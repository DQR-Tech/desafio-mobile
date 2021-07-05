//
//  NumberFormat+ConvertApp.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 22/04/21.
//

import Foundation

extension NumberFormatter {
    convenience init(numberStyle: Style) {
        self.init()
        self.numberStyle = numberStyle
    }
}
