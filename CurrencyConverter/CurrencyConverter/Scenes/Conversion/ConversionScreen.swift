//
//  CurrencyAmountConversionScreen.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

class ConversionScreen: UIView {
    
    var delegate: ConversionScreenDelegate?
    
    let topUnitSelectionButton = UnitSelectionButton(position: .top)
    let bottomUnitSelectionButton = UnitSelectionButton(position: .bottom)
        
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupSubviews()
        setupView()
        setupDelegates()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setupDelegates() {
        topUnitSelectionButton.delegate = self
        bottomUnitSelectionButton.delegate = self
    }
}

extension ConversionScreen: ParentCodeView {
    
    func setupView() {
        self.backgroundColor = .purple
    }
    
    func addSubviews() {
        addSubview(topUnitSelectionButton)
        addSubview(bottomUnitSelectionButton)
    }
    
    func setupConstraints() {
        setupTopUnitSelectionButtonConstraints()
        setupBottomUnitSelectionButtonConstraints()
    }
    
    func setupTopUnitSelectionButtonConstraints() {
        topUnitSelectionButton.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 50).isActive = true
        topUnitSelectionButton.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -50).isActive = true
        topUnitSelectionButton.topAnchor.constraint(equalTo: topAnchor, constant: 100).isActive = true
        topUnitSelectionButton.heightAnchor.constraint(equalToConstant: 100).isActive = true
    }
    
    func setupBottomUnitSelectionButtonConstraints() {
        bottomUnitSelectionButton.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 50).isActive = true
        bottomUnitSelectionButton.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -50).isActive = true
        bottomUnitSelectionButton.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -100).isActive = true
        bottomUnitSelectionButton.heightAnchor.constraint(equalToConstant: 100).isActive = true
    }
}

extension ConversionScreen: UnitSelectionButtonDelegate {
    
    func unitSelectionButtonPressed(for buttonPosition: UnitSelectionButtonPosition) {
        delegate?.unitSelectionButtonPressed(for: buttonPosition)
    }
}

protocol ConversionScreenDelegate {
    func unitSelectionButtonPressed(for buttonPosition: UnitSelectionButtonPosition)
}
