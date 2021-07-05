//
//  SelectionScreen.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

final class SelectionScreen: UIView {
    
    let unitSearchBar = UnitSearchBar()
    let availableUnitsTableView = AvailableUnitsTableView()
    
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupSubviews()
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension SelectionScreen: ParentCodeView {
    
    func setupView() {
        backgroundColor = .systemBackground
    }
    
    func addSubviews() {
        addSubview(unitSearchBar)
        addSubview(availableUnitsTableView)
    }
    
    func setupConstraints() {
        unitSearchBar.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        unitSearchBar.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
        unitSearchBar.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor).isActive = true
        
        availableUnitsTableView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        availableUnitsTableView.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
        availableUnitsTableView.topAnchor.constraint(equalTo: unitSearchBar.bottomAnchor).isActive = true
        availableUnitsTableView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
    }
}
