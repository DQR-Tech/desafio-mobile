//
//  ConversionScreenTableView.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 04/07/21.
//

import UIKit

final class ConversionScreen: UIView {
    
    lazy var unitsConversionTableView: UITableView = {
        let tableView = UITableView()
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.register(ConversionScreenTableViewCell.self, forCellReuseIdentifier: ConversionScreenTableViewCell.identifier)
        tableView.tableFooterView = UIView()
        return tableView
    }()
        
    override init(frame: CGRect = .zero) {
        super.init(frame: frame)
        setupSubviews()
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }    
}

extension ConversionScreen: ParentCodeView {
    
    func setupView() {
    }
    
    func addSubviews() {
        addSubview(unitsConversionTableView)
    }
    
    func setupConstraints() {
        unitsConversionTableView.leftAnchor.constraint(equalTo: leftAnchor).isActive = true
        unitsConversionTableView.rightAnchor.constraint(equalTo: rightAnchor).isActive = true
        unitsConversionTableView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        unitsConversionTableView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
    }
}
