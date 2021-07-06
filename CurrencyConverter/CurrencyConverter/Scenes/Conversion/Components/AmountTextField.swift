//
//  AmountTextField.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 04/07/21.
//

import UIKit

class AmountTextField: UITextField {
        
    // MARK: - Observed Properties
    
    var amount: Double? = nil {
        didSet {
            text = Decimal(amount!).currency
        }
    }
    
    var locale: Locale = .init(identifier: "en_US") {
        didSet {
            Formatter.currency.locale = locale
            sendActions(for: .editingChanged)
        }
    }
    
    // MARK: - Other properties
    
    var decimal: Decimal { string.decimal / pow(10, Formatter.currency.maximumFractionDigits) }
    var maximum: Decimal = 999_999_999_999.99
    private var lastValue: String?
    
    // MARK: - Initialize
    
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        layer.cornerRadius = 15.0
        layer.borderWidth = 3
        layer.borderColor = UIColor.systemGreen.withAlphaComponent(0.4).cgColor
        backgroundColor = UIColor.systemGreen.withAlphaComponent(0.4)
        translatesAutoresizingMaskIntoConstraints = false
        adjustsFontSizeToFitWidth = true
        minimumFontSize = 10
        font = UIFont.boldSystemFont(ofSize: 32)
        textAlignment = .center
        isExclusiveTouch = true
        addPadding(.both(20))
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Functionality
    
    override func willMove(toSuperview newSuperview: UIView?) {
        Formatter.currency.locale = locale
        addTarget(self, action: #selector(editingChanged), for: .editingChanged)
        keyboardType = .numberPad
        sendActions(for: .editingChanged)
    }
    
    override func deleteBackward() {
        text = string.digits.dropLast().string
        sendActions(for: .editingChanged)
    }
    
    @objc func editingChanged() {
        guard decimal <= maximum else {
            text = lastValue
            return
        }
        
        text = decimal.currency
        lastValue = text
    }
}

// MARK: - Extensions

extension AmountTextField {
    var doubleValue: Double { (decimal as NSDecimalNumber).doubleValue }
}

extension UITextField {
     var string: String { text ?? "" }
}

extension NumberFormatter {
    convenience init(numberStyle: Style) {
        self.init()
        self.numberStyle = numberStyle
    }
}

private extension Formatter {
    static let currency: NumberFormatter = .init(numberStyle: .currency)
}

extension StringProtocol where Self: RangeReplaceableCollection {
    var digits: Self { filter (\.isWholeNumber) }
}

extension String {
    var decimal: Decimal { Decimal(string: digits) ?? 0 }
}

extension Decimal {
    var currency: String { Formatter.currency.string(for: self) ?? "" }
}

extension LosslessStringConvertible {
    var string: String { .init(self) }
}
