//
//  CurrencyAmountTextField.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 04/07/21.
//

import UIKit

class CurrencyAmountTextField: UITextField {
        
    override init(frame: CGRect = .zero) {
        super.init(frame: .zero)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
        layer.borderColor = UIColor.label.cgColor
    }
}

extension CurrencyAmountTextField: CodeView {
    
    func setupView() {
        translatesAutoresizingMaskIntoConstraints = false
        backgroundColor = .clear
        
//        attributedText = NSAttributedString(
//            string: "$",
//            attributes: [
//                .foregroundColor: UIColor.systemBackground,
//                .font: UIFont.boldSystemFont(ofSize: 24.0)
//             ])
        
        
        font = UIFont.systemFont(ofSize: 24)
        heightAnchor.constraint(equalToConstant: 100).isActive = true
        setLeftPaddingPoints(20)
        layer.cornerRadius = 10
        layer.borderColor = UIColor.label.cgColor
        layer.borderWidth = 2.0
        textColor = .label
        text = "$ 323.023,50"
    }
}

extension UITextField {
    func setLeftPaddingPoints(_ amount:CGFloat = 0){
        let paddingView = UIView(frame: CGRect(x: 0, y: 0, width: amount, height: self.frame.size.height))
        self.leftView = paddingView
        self.leftViewMode = .always
    }
    func setRightPaddingPoints(_ amount:CGFloat = 0) {
        let paddingView = UIView(frame: CGRect(x: 0, y: 0, width: amount, height: self.frame.size.height))
        self.rightView = paddingView
        self.rightViewMode = .always
    }
}
