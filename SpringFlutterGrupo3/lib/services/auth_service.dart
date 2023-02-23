import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class AuthService extends ChangeNotifier {
  final String _baseUrl = 'localhost:8080';
  final storage = FlutterSecureStorage();

  Future<String?> register(
      String username, String password, String cpassword) async {
    final Map<String, dynamic> authData = {
      'username': username,
      'password': password,
      'cpassword': cpassword,
    };

    final url = Uri.http(_baseUrl, '/register', {});

    final resp = await http.post(url,
        headers: {
          'Content-type': 'application/json',
          'Accept': 'application/json',
          "Authorization": "Some token"
        },
        body: json.encode(authData));

    final Map<String, dynamic> decodeResp = json.decode(resp.body);

    if (decodeResp.containsKey(true)) {
      return null;
    } else {
      return decodeResp['message'];
    }
  }

  Future<String?> login(String username, String password) async {
    final Map<String, dynamic> authData = {
      'username': username,
      'password': password,
    };

    final url = Uri.http(_baseUrl, '/login', {});

    final resp = await http.post(url,
        headers: {
          'Content-type': 'application/json',
          'Accept': 'application/json',
          "Authorization": "Some token"
        },
        body: json.encode(authData));

    final Map<String, dynamic> decodeResp = json.decode(resp.body);
    if (decodeResp.containsValue(true)) {
      await storage.write(key: 'token', value: decodeResp['data']['token']);
      await storage.write(
          key: 'id', value: decodeResp['data']['id'].toString());

      // print(decodeResp['data']['token']);
      return decodeResp['data']['type'];
    } else {
      return null;
    }
  }

  Future<String> readToken() async {
    return await storage.read(key: 'token') ?? '';
  }

  Future<String> readId() async {
    return await storage.read(key: 'id') ?? '';
  }
}
