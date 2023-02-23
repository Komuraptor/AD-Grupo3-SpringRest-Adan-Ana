
import 'dart:convert';

class Pedidos {
    Pedidos({
         this.success,
        required this.data,
        required this.message,
    });

    bool? success;
    List<Datum> data;
    String message;

    factory Pedidos.fromJson(String str) => Pedidos.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Pedidos.fromMap(Map<String, dynamic> json) => Pedidos(
        success: json["success"],
        data: List<Datum>.from(json["data"].map((x) => Datum.fromMap(x))),
        message: json["message"],
    );

    Map<String, dynamic> toMap() => {
        "success": success,
        "data": List<dynamic>.from(data.map((x) => x.toMap())),
        "message": message,
    };
}

class Datum {
    Datum({
        required this.id,
        required this.articleId,
        required this.companyId,
        required this.compamyName,
        required this.compamyDescription,
        required this.price,
        required this.stock,
        required this.familyId,
        required this.deleted,
    });

    int id;
    int articleId;
    int companyId;
    String compamyName;
    String compamyDescription;
    String price;
    int stock;
    int familyId;
    int deleted;

    factory Datum.fromJson(String str) => Datum.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Datum.fromMap(Map<String, dynamic> json) => Datum(
        id: json["id"],
        articleId: json["article_id"],
        companyId: json["company_id"],
        compamyName: json["compamy_name"],
        compamyDescription: json["compamy_description"],
        price: json["price"],
        stock: json["stock"],
        familyId: json["family_id"],
        deleted: json["deleted"],
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "article_id": articleId,
        "company_id": companyId,
        "compamy_name": compamyName,
        "compamy_description": compamyDescription,
        "price": price,
        "stock": stock,
        "family_id": familyId,
        "deleted": deleted,
    };
}
