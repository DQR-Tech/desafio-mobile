//
//  CurrencyAmountConversionScreen.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

class ConversionScreen: UIView {
    
    var delegate: ConversionScreenDelegate?
    
    lazy var originUnitButton: UIButton = {
        let button = UIButton(frame: .zero)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.backgroundColor = .green
        button.setTitle("Select Origin Unit", for: .normal)
        button.addTarget(self, action: #selector(buttonPressed), for: .touchUpInside)
        return button
    }()
        
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupSubviews()
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    @objc
    func buttonPressed() {
        self.delegate?.buttonPressed()
    }
}

extension ConversionScreen: ParentCodeView {
    
    func setupView() {
        self.backgroundColor = .purple
    }
    
    func addSubviews() {
        addSubview(originUnitButton)
    }
    
    func setupConstraints() {
        originUnitButton.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 50).isActive = true
        originUnitButton.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -50).isActive = true
        originUnitButton.topAnchor.constraint(equalTo: topAnchor, constant: 100).isActive = true
        originUnitButton.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -100).isActive = true
        
    }
}

protocol ConversionScreenDelegate {
    func buttonPressed()
}
