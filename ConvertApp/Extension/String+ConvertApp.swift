//
//  String+ConvertApp.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 22/04/21.
//

import Foundation


extension StringProtocol where Self: RangeReplaceableCollection {
    var digits: Self { filter (\.isWholeNumber) }
}

extension String {
    var decimal: Decimal { Decimal(string: digits) ?? 0 }
}

extension LosslessStringConvertible {
    var string: String { .init(self) }
}
