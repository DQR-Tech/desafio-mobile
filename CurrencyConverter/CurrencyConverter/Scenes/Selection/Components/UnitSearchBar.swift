//
//  UnitSearchBar.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 05/07/21.
//

import UIKit

class UnitSearchBar: UISearchBar {
    
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension UnitSearchBar: CodeView {
    func setupView() {
        translatesAutoresizingMaskIntoConstraints = false
    }
}
