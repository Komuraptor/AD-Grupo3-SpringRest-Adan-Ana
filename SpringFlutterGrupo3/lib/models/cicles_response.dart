import 'dart:convert';

import 'package:trabajo_cop_flutter/models/models.dart';

class CiclesResponse {
    CiclesResponse({
        required this.success,
        required this.data,
        required this.message,
    });

    bool success;
    List<Ciclos> data;
    String message;

    factory CiclesResponse.fromJson(String str) => CiclesResponse.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory CiclesResponse.fromMap(Map<String, dynamic> json) => CiclesResponse(
        success: json["success"],
        data: List<Ciclos>.from(json["data"].map((x) => Ciclos.fromMap(x))),
        message: json["message"],
    );

    Map<String, dynamic> toMap() => {
        "success": success,
        "data": List<dynamic>.from(data.map((x) => x.toMap())),
        "message": message,
    };
}