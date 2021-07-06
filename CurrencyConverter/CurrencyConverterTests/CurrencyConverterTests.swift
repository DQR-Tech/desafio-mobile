//
//  CurrencyConverterTests.swift
//  CurrencyConverterTests
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import XCTest
@testable import CurrencyConverter

class CurrencyConverterTests: XCTestCase {

    override func setUpWithError() throws {
    }

    override func tearDownWithError() throws {
    }

    func testConversionControllerNotNill() throws {
        let vc = ConversionViewController()
        vc.viewDidLoad()
        XCTAssertNotNil(vc)
    }
    
    func testSelectionControllerNotNill() throws {
        let vc = SelectionViewController(for: 1)
        vc.viewDidLoad()
        XCTAssertNotNil(vc)
    }

    func testLoadTimeConversionScene() throws {
        // This is an example of a performance test case.
        self.measure {
            let vc = ConversionViewController()
            vc.viewDidLoad()
        }
    }
    
    func testLoadTimeSelectionScene() throws {
        // This is an example of a performance test case.
        self.measure {
            let vc = SelectionViewController(for: 1)
            vc.viewDidLoad()
        }
    }


}
