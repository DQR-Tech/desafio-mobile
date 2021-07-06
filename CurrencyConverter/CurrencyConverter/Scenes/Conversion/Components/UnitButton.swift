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
    
    override var isHighlighted: Bool {
        didSet {
            if isHighlighted {
                UIView.animate(withDuration: 0.3) {
                    self.backgroundColor = UIColor.systemGreen.withAlphaComponent(0.4)
                }
            } else {
                UIView.animate(withDuration: 0.3) {
                    self.backgroundColor = UIColor.systemGreen.withAlphaComponent(1)
                }
            }
        }
    }
    
    var delegate: UnitButtonDelegate?
    
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    @objc
    func buttonTouchUpInside() {
        self.delegate?.unitButtonPressed()
    }
}

extension UnitButton: CodeView {
    
    func setupView() {
        translatesAutoresizingMaskIntoConstraints = false
        setTitle("---", for: .normal)
        backgroundColor = UIColor.systemGreen
        titleLabel?.font = UIFont.boldSystemFont(ofSize: 32)
        layer.cornerRadius = 14
        isExclusiveTouch = true
        setTitleColor(.systemBackground, for: .normal)
        setTitleColor(.label, for: .highlighted)
        addTarget(self, action: #selector(buttonTouchUpInside), for: .touchUpInside)
    }
}

protocol UnitButtonDelegate {
    func unitButtonPressed()
}
