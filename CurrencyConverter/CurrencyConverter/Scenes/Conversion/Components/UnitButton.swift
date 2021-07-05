//
//  UnitButton.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 04/07/21.
//

import UIKit

class UnitButton: UIButton {
    
    var unit: String? {
        didSet {
            setTitle(unit, for: .normal)
        }
    }
    
    var delegate: UnitButtonDelegate?
    
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupView()
        addTarget(self, action: #selector(buttonTouchDown), for: .touchDown)
        addTarget(self, action: #selector(buttonTouchUpInside), for: .touchUpInside)
        addTarget(self, action: #selector(buttonTouchUpInside), for: .touchUpOutside)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    @objc
    func buttonTouchDown() {
        backgroundColor = .systemGray
        setTitleColor(.label, for: .normal)
        setTitleColor(.label, for: .highlighted)
        self.delegate?.unitButtonPressed()
    }
    
    @objc
    func buttonTouchUpInside() {
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.15) {
            self.backgroundColor = .systemGreen
            self.setTitleColor(.systemBackground, for: .normal)
            self.setTitleColor(.systemBackground, for: .highlighted)
        }
    }
}

extension UnitButton: CodeView {
    
    func setupView() {
        translatesAutoresizingMaskIntoConstraints = false
        setTitle("USD", for: .normal)
        setTitleColor(.systemBackground, for: .normal)
        backgroundColor = UIColor.systemGreen
        titleLabel?.font = UIFont.boldSystemFont(ofSize: 20)
    }
}

protocol UnitButtonDelegate {
    func unitButtonPressed()
}
