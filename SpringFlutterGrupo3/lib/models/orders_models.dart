
import 'dart:convert';

class Orders {
    Orders({
        required this.success,
        required this.data,
        required this.message,
    });

    bool success;
    List<Datum2> data;
    String message;

    factory Orders.fromJson(String str) => Orders.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Orders.fromMap(Map<String, dynamic> json) => Orders(
        success: json["success"],
        data: List<Datum2>.from(json["data"].map((x) => Datum2.fromMap(x))),
        message: json["message"],
    );

    Map<String, dynamic> toMap() => {
        "success": success,
        "data": List<dynamic>.from(data.map((x) => x.toMap())),
        "message": message,
    };
}

class Datum2 {
    Datum2({
        required this.id,
        required this.originCompanyId,
        required this.orderLines,
    });

    int id;
    int originCompanyId;
    List<OrderLine> orderLines;

    factory Datum2.fromJson(String str) => Datum2.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Datum2.fromMap(Map<String, dynamic> json) => Datum2(
        id: json["id"],
        originCompanyId: json["origin_company_id"],
        orderLines: List<OrderLine>.from(json["order_lines"].map((x) => OrderLine.fromMap(x))),
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "origin_company_id": originCompanyId,
        "order_lines": List<dynamic>.from(orderLines.map((x) => x.toMap())),
    };
}

class OrderLine {
    OrderLine({
        required this.id,
        required this.orderId,
        required this.orderLineNum,
        required this.issueDate,
        required this.deleted,
        required this.createdAt,
        required this.updatedAt,
        required this.articlesLine,
    });

    int id;
    int orderId;
    String orderLineNum;
    DateTime issueDate;
    int deleted;
    DateTime createdAt;
    DateTime updatedAt;
    List<ArticlesLine> articlesLine;

    factory OrderLine.fromJson(String str) => OrderLine.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory OrderLine.fromMap(Map<String, dynamic> json) => OrderLine(
        id: json["id"],
        orderId: json["order_id"],
        orderLineNum: json["order_line_num"],
        issueDate: DateTime.parse(json["issue_date"]),
        deleted: json["deleted"],
        createdAt: DateTime.parse(json["created_at"]),
        updatedAt: DateTime.parse(json["updated_at"]),
        articlesLine: List<ArticlesLine>.from(json["articles_line"].map((x) => ArticlesLine.fromMap(x))),
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "order_id": orderId,
        "order_line_num": orderLineNum,
        "issue_date": "${issueDate.year.toString().padLeft(4, '0')}-${issueDate.month.toString().padLeft(2, '0')}-${issueDate.day.toString().padLeft(2, '0')}",
        "deleted": deleted,
        "created_at": createdAt.toIso8601String(),
        "updated_at": updatedAt.toIso8601String(),
        "articles_line": List<dynamic>.from(articlesLine.map((x) => x.toMap())),
    };
}

class ArticlesLine {
    ArticlesLine({
        required this.id,
        required this.articleId,
        required this.numArticles,
        required this.orderLinesId,
        required this.deleted,
        required this.updatedAt,
        required this.createdAt,
        required this.article,
    });

    int id;
    int articleId;
    int numArticles;
    int orderLinesId;
    int deleted;
    DateTime updatedAt;
    DateTime createdAt;
    Article article;

    factory ArticlesLine.fromJson(String str) => ArticlesLine.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory ArticlesLine.fromMap(Map<String, dynamic> json) => ArticlesLine(
        id: json["id"],
        articleId: json["article_id"],
        numArticles: json["num_articles"],
        orderLinesId: json["order_lines_id"],
        deleted: json["deleted"],
        updatedAt: DateTime.parse(json["updated_at"]),
        createdAt: DateTime.parse(json["created_at"]),
        article: Article.fromMap(json["article"]),
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "article_id": articleId,
        "num_articles": numArticles,
        "order_lines_id": orderLinesId,
        "deleted": deleted,
        "updated_at": updatedAt.toIso8601String(),
        "created_at": createdAt.toIso8601String(),
        "article": article.toMap(),
    };
}

class Article {
    Article({
        required this.id,
        required this.name,
        required this.description,
        required this.priceMin,
        required this.priceMax,
        required this.colorName,
        required this.weight,
        required this.size,
        required this.familyId,
        required this.deleted,
        this.createdAt,
        this.updatedAt,
    });

    int id;
    Name name;
    Description description;
    String priceMin;
    String priceMax;
    ColorName colorName;
    String weight;
    Size size;
    int familyId;
    int deleted;
    DateTime? createdAt;
    DateTime? updatedAt;

    factory Article.fromJson(String str) => Article.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Article.fromMap(Map<String, dynamic> json) => Article(
        id: json["id"],
        name: nameValues.map[json["name"]]!,
        description: descriptionValues.map[json["description"]]!,
        priceMin: json["price_min"],
        priceMax: json["price_max"],
        colorName: colorNameValues.map[json["color_name"]]!,
        weight: json["weight"],
        size: sizeValues.map[json["size"]]!,
        familyId: json["family_id"],
        deleted: json["deleted"],
        createdAt: json["created_at"] == null ? null : DateTime.parse(json["created_at"]),
        updatedAt: json["updated_at"] == null ? null : DateTime.parse(json["updated_at"]),
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "name": nameValues.reverse[name],
        "description": descriptionValues.reverse[description],
        "price_min": priceMin,
        "price_max": priceMax,
        "color_name": colorNameValues.reverse[colorName],
        "weight": weight,
        "size": sizeValues.reverse[size],
        "family_id": familyId,
        "deleted": deleted,
        "created_at": createdAt?.toIso8601String(),
        "updated_at": updatedAt?.toIso8601String(),
    };
}

enum ColorName { AMARILLO, BLANCO, VERDE, OCRE, VIOLETA, AZUL, EMPTY }

final colorNameValues = EnumValues({
    "Amarillo": ColorName.AMARILLO,
    "Azul": ColorName.AZUL,
    "Blanco": ColorName.BLANCO,
    "": ColorName.EMPTY,
    "Ocre": ColorName.OCRE,
    "Verde": ColorName.VERDE,
    "Violeta": ColorName.VIOLETA
});

enum Description { PINTURA_AMARILLA_2_KG, PINTURA_BLANCA_12_KG, AZULEJO_VERDE_60_X60, PINTURA_OCRE_25_KG, BALDOSA_VIOLETA_20_X20, AZULEJO_AZUL_15_X15, BROCHA_5_CM, RODILLO_10_CM }

final descriptionValues = EnumValues({
    "Azulejo Azul 15x15": Description.AZULEJO_AZUL_15_X15,
    "Azulejo Verde 60x60": Description.AZULEJO_VERDE_60_X60,
    "Baldosa Violeta 20x20": Description.BALDOSA_VIOLETA_20_X20,
    "Brocha 5cm": Description.BROCHA_5_CM,
    "Pintura Amarilla 2Kg": Description.PINTURA_AMARILLA_2_KG,
    "Pintura blanca 1/2 Kg": Description.PINTURA_BLANCA_12_KG,
    "Pintura Ocre 25Kg": Description.PINTURA_OCRE_25_KG,
    "Rodillo 10cm": Description.RODILLO_10_CM
});

enum Name { PINT_AM_2, PINT_BL_05, AZU_VER_6060, PINT_OC_25, BAL_VI_2020, AZU_AZ_1515, BRO_5, ROD_10 }

final nameValues = EnumValues({
    "Azu_az_15_15": Name.AZU_AZ_1515,
    "Azu_ver_60_60": Name.AZU_VER_6060,
    "Bal_vi_20_20": Name.BAL_VI_2020,
    "Bro_5": Name.BRO_5,
    "Pint_Am_2": Name.PINT_AM_2,
    "Pint_Bl_0_5": Name.PINT_BL_05,
    "Pint_Oc_25": Name.PINT_OC_25,
    "Rod_10": Name.ROD_10
});

enum Size { EMPTY, THE_60_X60, THE_250, THE_20_X20, THE_15_X15, THE_5_CM, THE_10_CM }

final sizeValues = EnumValues({
    "": Size.EMPTY,
    "10cm": Size.THE_10_CM,
    "15x15": Size.THE_15_X15,
    "20x20": Size.THE_20_X20,
    "25,0": Size.THE_250,
    "5cm": Size.THE_5_CM,
    "60x60": Size.THE_60_X60
});

class EnumValues<T> {
    Map<String, T> map;
    late Map<T, String> reverseMap;

    EnumValues(this.map);

    Map<T, String> get reverse {
        reverseMap = map.map((k, v) => MapEntry(v, k));
        return reverseMap;
    }
}
