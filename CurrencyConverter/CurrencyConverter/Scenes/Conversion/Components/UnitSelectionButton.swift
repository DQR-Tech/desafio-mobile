//
//  UnitSelectionButton.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 03/07/21.
//

import UIKit

public enum UnitSelectionButtonPosition: String {
    case top = "Top"
    case bottom = "Bottom"
}

class UnitSelectionButton: UIButton {
    
    var buttonPosition: UnitSelectionButtonPosition?
    var delegate: UnitSelectionButtonDelegate?
    
    init(position buttonPosition: UnitSelectionButtonPosition, frame: CGRect = .zero) {
        super.init(frame: .zero)
        self.buttonPosition = buttonPosition
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    @objc
    func buttomPressed() {
        delegate?.unitSelectionButtonPressed(for: buttonPosition!)
    }
}

extension UnitSelectionButton: CodeView {
    
    func setupView() {
        translatesAutoresizingMaskIntoConstraints = false
        backgroundColor = .green
        setTitle("USD", for: .normal)
        addTarget(self, action: #selector(buttomPressed), for: .touchUpInside)
    }
}

protocol UnitSelectionButtonDelegate {
    func unitSelectionButtonPressed(for buttonPosition: UnitSelectionButtonPosition)
}
