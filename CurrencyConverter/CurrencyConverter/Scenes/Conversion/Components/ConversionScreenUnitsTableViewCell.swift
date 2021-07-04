//
//  ConversionScreenTableViewCell.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 04/07/21.
//

import UIKit

class ConversionScreenUnitsTableViewCell: UITableViewCell {
    
    // MARK: - Static properties
    
    static let identifier = "ConversionScreenTableViewCell"
    
    // MARK: - Instantiate visual elements
    
    let unitButton = UnitButton()
    let amountTextField = AmountTextField()
        
    // MARK: - Initialize cell
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupView()
        setupSubviews()
        amountTextField.locale = Locale(identifier: "en_US")
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}


// MARK: - ParentCodeView

extension ConversionScreenUnitsTableViewCell: ParentCodeView {
    func addSubviews() {
        contentView.addSubview(unitButton)
        contentView.addSubview(amountTextField)
    }
    
    func setupConstraints() {
        unitButton.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        unitButton.widthAnchor.constraint(equalToConstant: 150).isActive = true
        unitButton.topAnchor.constraint(equalTo: topAnchor).isActive = true
        unitButton.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true

        
//        amountTextField.leadingAnchor.constraint(equalTo: unitButton.trailingAnchor).isActive = true
        amountTextField.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20).isActive = true
        amountTextField.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        amountTextField.heightAnchor.constraint(equalToConstant: 40).isActive = true
    }
    
    func setupView() {
    }
}
