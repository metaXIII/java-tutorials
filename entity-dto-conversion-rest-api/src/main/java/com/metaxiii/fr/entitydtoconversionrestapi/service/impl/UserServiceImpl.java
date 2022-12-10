package com.metaxiii.fr.entitydtoconversionrestapi.service.impl;

import com.metaxiii.fr.entitydtoconversionrestapi.model.Preference;
import com.metaxiii.fr.entitydtoconversionrestapi.model.User;
import com.metaxiii.fr.entitydtoconversionrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  @Override
  public User getCurrentUser() {
    Preference preference = new Preference();
    preference.setId(1L);
    preference.setTimezone("Europe/Paris");
    User user = new User();
    user.setId(1L);
    user.setName("metaxiii");
    user.setPreference(preference);
    return user;
  }
}
