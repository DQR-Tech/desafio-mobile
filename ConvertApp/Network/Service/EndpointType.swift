//
//  EndpointType.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 21/04/21.
//

import Foundation

protocol EndPointType {
    var baseURL: URL { get }
    var path: String { get }
    var httpMethod: HTTPMethod { get }
    var task: HTTPTask { get }
    var headers: HTTPHeaders? { get }
}
