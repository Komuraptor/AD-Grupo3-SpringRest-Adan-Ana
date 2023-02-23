import 'dart:convert';

class Articles {
  Articles({
    required this.id,
    required this.name,
    required this.description,
    required this.price_min,
    required this.price_max,
    required this.color_name,
    required this.weight,
    required this.size,
    required this.family_id,
    required this.deleted,
  });

  int id;
  String name;
  String description;
  String price_min;
  String price_max;
  String color_name;
  String weight;
  String size;
  int family_id;
  int deleted;

  factory Articles.fromJson(String str) => Articles.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Articles.fromMap(Map<String, dynamic> json) => Articles(
        id: json["id"],
        name: json["name"],
        description: json["description"],
        price_min: json["price_min"],
        price_max: json["price_max"],
        color_name: json["color_name"],
        weight: json["weight"],
        size: json["size"],
        family_id: json["family_id"],
        deleted: json["deleted"],
      );

  Map<String, dynamic> toMap() => {
        "id": id,
        "name": name,
        "description": description,
        "price_min": price_min,
        "price_max": price_max,
        "color_name": color_name,
        "weight": weight,
        "size": size,
        "family_id": family_id,
        "deleted": deleted,
      };
}
