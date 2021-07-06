//
//  ConversionUnitsTableView.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 04/07/21.
//

import UIKit

class UnitsConversionTableView: UITableView {
    
    init(frame: CGRect = .zero) {
        super.init(frame: .zero, style: .plain)
        translatesAutoresizingMaskIntoConstraints = false
        register(UnitsConversionTableViewCell.self, forCellReuseIdentifier: UnitsConversionTableViewCell.identifier)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
