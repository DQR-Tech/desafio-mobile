////
////  CurrencyAmountConversionScreen.swift
////  CurrencyConverter
////
////  Created by Lucas Werner Kuipers on 02/07/21.
////
//
//import UIKit
//
//final class ConversionScreen: UIView {
//
//    var delegate: ConversionScreenDelegate?
//
//    let topUnitSelectionButton = UnitSelectionButton(position: .top)
//    let bottomUnitSelectionButton = UnitSelectionButton(position: .bottom)
//    let topCurrencyAmountTextField = CurrencyAmountTextField()
//    let bottomCurrencyAmountTextField = CurrencyAmountTextField()
//
//    let scrollView: UIScrollView = {
//        let scrollView = UIScrollView()
//        scrollView.translatesAutoresizingMaskIntoConstraints = false
//        return scrollView
//    }()
//
//    let scrollViewContainer: UIStackView = {
//        let view = UIStackView()
//
//        view.axis = .vertical
//        view.spacing = 10
//
//        view.translatesAutoresizingMaskIntoConstraints = false
//        return view
//    }()
//
//    let greenView: UIView = {
//        let view = UIView()
//        view.heightAnchor.constraint(equalToConstant: 150).isActive = true
//        view.backgroundColor = .green
//        return view
//    }()
//
//    let redView: UIView = {
//        let view = UIView()
//        view.heightAnchor.constraint(equalToConstant: 150).isActive = true
//        view.backgroundColor = .red
//        return view
//    }()
//
//    let blueView: UIView = {
//        let view = UIView()
//        let blueHeight = UIScreen.main.bounds.height - 300
//        view.heightAnchor.constraint(equalToConstant: blueHeight).isActive = true
//        view.backgroundColor = .blue
//        return view
//    }()
//
//    override init(frame: CGRect = .zero) {
//        super.init(frame: frame)
//        setupSubviews()
//        setupView()
//        setupDelegates()
//    }
//
//    required init?(coder: NSCoder) {
//        fatalError("init(coder:) has not been implemented")
//    }
//
//    func setupDelegates() {
//        topUnitSelectionButton.delegate = self
//        bottomUnitSelectionButton.delegate = self
//    }
//}
//
//extension ConversionScreen: ParentCodeView {
//
//    func setupView() {
//        self.backgroundColor = .systemBackground
//    }
//
//    func addSubviews() {
//        addSubview(scrollView)
//        scrollView.addSubview(scrollViewContainer)
//        scrollViewContainer.addArrangedSubview(greenView)
//        scrollViewContainer.addArrangedSubview(redView)
//        scrollViewContainer.addArrangedSubview(blueView)
//
//        greenView.addSubview(topUnitSelectionButton)
//        greenView.addSubview(topCurrencyAmountTextField)
//
//        redView.addSubview(bottomUnitSelectionButton)
//        redView.addSubview(bottomCurrencyAmountTextField)
//
////        addSubview(topUnitSelectionButton)
////        addSubview(bottomUnitSelectionButton)
////        addSubview(topCurrencyAmountTextField)
//    }
//
//    func setupConstraints() {
//        setupScrollViewConstraints()
//        setupScrollViewContainerConstraints()
//        setupTopUnitSelectionButtonConstraints()
//        setupBottomUnitSelectionButtonConstraints()
//        setupTopCurrencyAmountTextFieldConstraints()
//        setupBottomCurrencyAmountTextFieldConstraints()
//    }
//
//    func setupScrollViewContainerConstraints() {
//        scrollViewContainer.leadingAnchor.constraint(equalTo: scrollView.leadingAnchor).isActive = true
//        scrollViewContainer.trailingAnchor.constraint(equalTo: scrollView.trailingAnchor).isActive = true
//        scrollViewContainer.topAnchor.constraint(equalTo: scrollView.topAnchor).isActive = true
//        scrollViewContainer.bottomAnchor.constraint(equalTo: scrollView.bottomAnchor).isActive = true
//
//        scrollViewContainer.widthAnchor.constraint(equalTo: scrollView.widthAnchor).isActive = true
//    }
//
//    func setupScrollViewConstraints() {
//        scrollView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
//        scrollView.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
//        scrollView.topAnchor.constraint(equalTo: topAnchor).isActive = true
//        scrollView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
//    }
//
//    func setupTopUnitSelectionButtonConstraints() {
//        topUnitSelectionButton.leadingAnchor.constraint(equalTo: greenView.leadingAnchor, constant: 20).isActive = true
//        topUnitSelectionButton.topAnchor.constraint(equalTo: greenView.topAnchor, constant: 20).isActive = true
////        topUnitSelectionButton.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 50).isActive = true
////        topUnitSelectionButton.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -50).isActive = true
////        topUnitSelectionButton.topAnchor.constraint(equalTo: topAnchor, constant: 100).isActive = true
////        topUnitSelectionButton.heightAnchor.constraint(equalToConstant: 100).isActive = true
//    }
//
//    func setupBottomUnitSelectionButtonConstraints() {
//        bottomUnitSelectionButton.leadingAnchor.constraint(equalTo: redView.leadingAnchor, constant: 20).isActive = true
//        bottomUnitSelectionButton.topAnchor.constraint(equalTo: redView.topAnchor, constant: 20).isActive = true
//    }
//
//    func setupTopCurrencyAmountTextFieldConstraints() {
//        topCurrencyAmountTextField.leftAnchor.constraint(equalTo: topUnitSelectionButton.rightAnchor, constant: 20).isActive = true
//        topCurrencyAmountTextField.rightAnchor.constraint(equalTo: rightAnchor, constant: -20).isActive = true
//        topCurrencyAmountTextField.topAnchor.constraint(equalTo: greenView.topAnchor, constant: 20).isActive = true
//    }
//
//    func setupBottomCurrencyAmountTextFieldConstraints() {
//        bottomCurrencyAmountTextField.leftAnchor.constraint(equalTo: bottomUnitSelectionButton.rightAnchor, constant: 20).isActive = true
//        bottomCurrencyAmountTextField.rightAnchor.constraint(equalTo: rightAnchor, constant: -20).isActive = true
//        bottomCurrencyAmountTextField.topAnchor.constraint(equalTo: redView.topAnchor, constant: 20).isActive = true
//    }
//}
//
//extension ConversionScreen: UnitSelectionButtonDelegate {
//    func unitSelectionButtonPressed(for buttonPosition: UnitSelectionButtonPosition) {
//        delegate?.unitSelectionButtonPressed(for: buttonPosition)
//    }
//}
//
//protocol ConversionScreenDelegate {
//    func unitSelectionButtonPressed(for buttonPosition: UnitSelectionButtonPosition)
//}
