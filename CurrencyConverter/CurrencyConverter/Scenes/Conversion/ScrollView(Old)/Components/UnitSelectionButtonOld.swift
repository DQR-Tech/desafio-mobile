////
////  UnitSelectionButton.swift
////  CurrencyConverter
////
////  Created by Lucas Werner Kuipers on 03/07/21.
////
//
//import UIKit
//
//public enum UnitSelectionButtonPosition: String {
//    case top = "Top"
//    case bottom = "Bottom"
//}
//
//class UnitSelectionButton: UIButton {
//    
//    var buttonPosition: UnitSelectionButtonPosition?
//    var delegate: UnitSelectionButtonDelegate?
//    
//    init(position buttonPosition: UnitSelectionButtonPosition, frame: CGRect = .zero) {
//        super.init(frame: .zero)
//        self.buttonPosition = buttonPosition
//        setupView()
//    }
//    
//    required init?(coder: NSCoder) {
//        fatalError("init(coder:) has not been implemented")
//    }
//    
//    override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
//        setBackgroundColor(color: .label, forState: .normal)
//        setTitleColor(.systemBackground, for: .normal)
//        setTitleColor(.label, for: .highlighted)
//
//    }
//    
//    @objc
//    func buttomTapped() {
//        backgroundColor = .gray
//        setTitleColor(.systemBackground, for: .normal)
//        delegate?.unitSelectionButtonPressed(for: buttonPosition!)
//    }
//    
//    @objc
//    func buttomPressed() {
//        backgroundColor = .gray
//    }
//    
//    @objc
//    func buttomReleased() {
//        backgroundColor = .label
//    }
//}
//
//extension UnitSelectionButton: CodeView {
//    
//    func setupView() {
//        translatesAutoresizingMaskIntoConstraints = false
//        backgroundColor = .label
//        setTitleColor(.systemBackground, for: .normal)
//        setTitleColor(.label, for: .highlighted)
//        setBackgroundColor(color: .label, forState: .normal)
//        setBackgroundColor(color: .gray, forState: .highlighted)
////        setBackgroundColor(color: .gray, forState: .highlighted)
//        setTitle("USD", for: .normal)
//        addTarget(self, action: #selector(buttomTapped), for: .touchUpInside)
//        addTarget(self, action: #selector(buttomPressed), for: .touchDown)
//
//        addTarget(self, action: #selector(buttomReleased), for: .touchUpOutside)
//        titleLabel?.font = UIFont.boldSystemFont(ofSize: 24)
//        layer.cornerRadius = 10
//        widthAnchor.constraint(equalToConstant: 100).isActive = true
//        heightAnchor.constraint(equalToConstant: 100).isActive = true
//        clipsToBounds = true
//    }
//}
//
//protocol UnitSelectionButtonDelegate {
//    func unitSelectionButtonPressed(for buttonPosition: UnitSelectionButtonPosition)
//}
//
//extension UIButton {
//    func setBackgroundColor(color: UIColor, forState: UIControl.State) {
//        self.clipsToBounds = true  // add this to maintain corner radius
//        UIGraphicsBeginImageContext(CGSize(width: 1, height: 1))
//        if let context = UIGraphicsGetCurrentContext() {
//            context.setFillColor(color.cgColor)
//            context.fill(CGRect(x: 0, y: 0, width: 1, height: 1))
//            let colorImage = UIGraphicsGetImageFromCurrentImageContext()
//            UIGraphicsEndImageContext()
//            self.setBackgroundImage(colorImage, for: forState)
//        }
//    }
//}
