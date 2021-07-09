//
//  UIStackView+Extension.swift
//  DesafioBTG
//
//  Created by Euclides Medeiros on 09/07/21.
//

import Foundation
import UIKit

extension UIStackView {
    func changeBackgroundColor(color: UIColor) {
        let backgroundLayer = CAShapeLayer()
        self.layer.insertSublayer(backgroundLayer, at: 0)
        backgroundLayer.path = UIBezierPath(rect: self.bounds).cgPath
        backgroundLayer.fillColor = color.cgColor
    }
}
