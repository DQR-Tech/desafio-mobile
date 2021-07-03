//
//  ParentCodeView.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

protocol ParentCodeView: CodeView {
    func addSubviews()
    func setupConstraints()
    func setupSubviews()
}

extension ParentCodeView {
    func setupSubviews() {
        addSubviews()
        setupConstraints()
    }
}
