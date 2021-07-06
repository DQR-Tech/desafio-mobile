//
//  AvailableUnitsTableView.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 05/07/21.
//

import UIKit

class AvailableUnitsTableView: UITableView {
    
    init(frame: CGRect = .zero) {
        super.init(frame: .zero, style: .plain)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension AvailableUnitsTableView: CodeView {
    func setupView() {
        translatesAutoresizingMaskIntoConstraints = false
        register(AvailableUnitsTableViewCell.self, forCellReuseIdentifier: AvailableUnitsTableViewCell.identifier)
        tableFooterView = UIView()
        keyboardDismissMode = .onDrag
    }
}
