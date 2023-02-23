import 'dart:convert';

class Pedidos {
  Pedidos({
    required this.id,
    required this.num,
    required this.target_company_name,
    required this.created_at,
    required this.issue_date,
    required this.delivery_notes,
    required this.invoices,
    //icono del albaran y de la factura en en la screen
  });

  int id;
  String num;
  String target_company_name;
  String created_at;
  String issue_date;
  int delivery_notes; 
  int invoices;

  factory Pedidos.fromJson(String str) => Pedidos.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Pedidos.fromMap(Map<String, dynamic> json) => Pedidos(
        id: json["id"],
        num: json["num"],
        target_company_name: json["target_company_name"],
        created_at: json["created_at"],
        issue_date: json["issue_date"],
        delivery_notes: json["delivery_notes"],
        invoices: json["invoices"],
      );

  Map<String, dynamic> toMap() => {
        "id": id,
        "num": num,
        "target_company_name": target_company_name,
        "created_at": created_at,
        "issue_date": issue_date,
        "delivery_notes": delivery_notes,
        "invoices": invoices,
      };
}
