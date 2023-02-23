import 'dart:convert';

import 'package:trabajo_cop_flutter/models/models.dart';

class ArticlesResponse {
  ArticlesResponse({
     this.success,
    required this.data,
    required this.message,
  });

  bool? success;
  List<Articles> data;
  String message;

  factory ArticlesResponse.fromJson(String str) =>
      ArticlesResponse.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory ArticlesResponse.fromMap(Map<String, dynamic> json) =>
      ArticlesResponse(
        success: json["success"],
        data: List<Articles>.from(json["data"].map((x) => Articles.fromMap(x))),
        message: json["message"],
      );

  Map<String, dynamic> toMap() => {
        "success": success,
        "data": List<dynamic>.from(data.map((x) => x.toMap())),
        "message": message,
      };
}
