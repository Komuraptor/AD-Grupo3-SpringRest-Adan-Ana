import 'dart:convert';

class UserData {
    UserData({
        this.success,
        required this.data,
        required this.message,
    });

    bool? success;
    Data data;
    String message;

    factory UserData.fromJson(String str) => UserData.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory UserData.fromMap(Map<String, dynamic> json) => UserData(
        success: json["success"],
        data: Data.fromMap(json["data"]),
        message: json["message"],
    );

    Map<String, dynamic> toMap() => {
        "success": success,
        "data": data.toMap(),
        "message": message,
    };
}

class Data {
    Data({
        required this.id,
        required this.firstname,
        required this.secondname,
        required this.companyId,
        required this.actived,
        required this.email,
        required this.type,
        required this.emailConfirmed,
        required this.deleted,
        required this.iscontact,
        required this.company,
        required this.createdAt,
    });

    int id;
    String firstname;
    String secondname;
    int companyId;
    int actived;
    String email;
    String type;
    int emailConfirmed;
    int deleted;
    int iscontact;
    String company;
    DateTime createdAt;

    factory Data.fromJson(String str) => Data.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Data.fromMap(Map<String, dynamic> json) => Data(
        id: json["id"],
        firstname: json["firstname"],
        secondname: json["secondname"],
        companyId: json["company_id"],
        actived: json["actived"],
        email: json["email"],
        type: json["type"],
        emailConfirmed: json["email_confirmed"],
        deleted: json["deleted"],
        iscontact: json["iscontact"],
        company: json["company"],
        createdAt: DateTime.parse(json["created_at"]),
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "firstname": firstname,
        "secondname": secondname,
        "company_id": companyId,
        "actived": actived,
        "email": email,
        "type": type,
        "email_confirmed": emailConfirmed,
        "deleted": deleted,
        "iscontact": iscontact,
        "company": company,
        "created_at": createdAt.toIso8601String(),
    };
}