//
//  ConversionScreenTableViewCell.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 04/07/21.
//

import UIKit

class UnitsConversionTableViewCell: UITableViewCell {
    
    var delegate: UnitsConversionTableViewCellDelegate?
    
    // MARK: - Static properties
    
    static let identifier = "ConversionScreenTableViewCell"
    var position: Int?
    var unit: String? {
        didSet {
            unitButton.unit = unit
        }
    }
    var amount: Double? {
        didSet {
            amountTextField.amount = amount
        }
    }
        
    // MARK: - Instantiate visual elements
    
    let unitButton = UnitButton()
    let amountTextField = AmountTextField()
        
    // MARK: - Initialize cell
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupView()
        setupSubviews()
        setupDelegates()
        amountTextField.locale = Locale(identifier: "en_US")
        selectionStyle = .none
        amountTextField.addTarget(self, action: #selector(textFieldDidChange), for: .editingChanged)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setupDelegates() {
        unitButton.delegate = self
        amountTextField.delegate = self
    }
}

// MARK: - ParentCodeView

extension UnitsConversionTableViewCell: ParentCodeView {
    func addSubviews() {
        contentView.addSubview(unitButton)
        contentView.addSubview(amountTextField)
    }
    
    func setupConstraints() {
        unitButton.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        unitButton.widthAnchor.constraint(equalToConstant: 150).isActive = true
        unitButton.topAnchor.constraint(equalTo: topAnchor).isActive = true
        unitButton.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true

        amountTextField.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20).isActive = true
        amountTextField.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        amountTextField.heightAnchor.constraint(equalToConstant: 40).isActive = true
    }
    
    func setupView() {
    }
}

extension UnitsConversionTableViewCell: UnitButtonDelegate {
    func unitButtonPressed() {
        if position != nil {
            print("Cell of position: \(position!) pressed!")
        }
    }
}

extension UnitsConversionTableViewCell: UITextFieldDelegate {
    @objc
    func textFieldDidChange(_ textField: UITextField) {
        guard let originText = textField.text else { return }
        guard let originAmount = Double(originText.filter("0123456789.".contains)) else { return }
        guard let originUnit = unit else { return }
        guard let originRate = Currency.conversionRates[originUnit] else { return }
        guard let position = position else { return }
        
        print("Origin amount: \(originAmount) of unit \(originUnit) in cell position: \(position)")
        let dollarAmount = originAmount / originRate
        
        print("Amount given in dollars: \(dollarAmount)")
        
        self.delegate?.amountDidChange(on: position, to: dollarAmount)
    }
}

protocol UnitsConversionTableViewCellDelegate {
    func amountDidChange(on cellPosition: Int, to amountInDollars: Double)
}
