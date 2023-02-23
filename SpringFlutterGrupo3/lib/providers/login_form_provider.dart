import 'package:flutter/material.dart';
import 'package:trabajo_cop_flutter/models/models.dart';

class LoginFormProvider extends ChangeNotifier {

  GlobalKey<FormState> formKey = new GlobalKey<FormState>();
  String name='';
  String surname='';
  String email='';
  String password='';
  String cpassword='';
  int cicleid=0;


  bool _isLoading = false;
  bool get isLoading =>_isLoading;
  
  set isLoading( bool value) {
    _isLoading=value;
    notifyListeners();
  }


  bool isValidForm() {
    
    return formKey.currentState?.validate() ?? false;
  }
}