import 'dart:convert';

import 'package:trabajo_cop_flutter/models/models.dart';
import 'package:trabajo_cop_flutter/models/pedidos_models.dart';

class PedidosResponse {
  PedidosResponse({
    this.success,
    required this.data,
    required this.message,
  });

  bool? success;
  List<Pedidos> data;
  String message;

  factory PedidosResponse.fromJson(String str) =>
      PedidosResponse.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory PedidosResponse.fromMap(Map<String, dynamic> json) => PedidosResponse(
        success: json["success"],
        data: List<Pedidos>.from(json["data"].map((x) => Pedidos.fromMap(x))),
        message: json["message"],
      );

  Map<String, dynamic> toMap() => {
        "success": success,
        "data": List<dynamic>.from(data.map((x) => x.toMap())),
        "message": message,
      };
}
