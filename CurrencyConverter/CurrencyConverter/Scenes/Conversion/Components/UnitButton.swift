//
//  UnitButton.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 04/07/21.
//

import UIKit

class UnitButton: UIButton {
    
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension UnitButton: CodeView {
    
    func setupView() {
        translatesAutoresizingMaskIntoConstraints = false
        setTitle("USD", for: .normal)
        setTitleColor(.systemBackground, for: .normal)
        setTitleColor(.gray, for: .highlighted)
        backgroundColor = UIColor.systemGreen
        titleLabel?.font = UIFont.boldSystemFont(ofSize: 20)
    }
}
