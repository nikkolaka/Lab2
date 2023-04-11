package edu.uw.tcss450.uiandnavigationlab.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.auth0.android.jwt.JWT;

public class UserInfoViewModel extends androidx.lifecycle.ViewModel{
    private final JWT mJwt;



    private UserInfoViewModel(JWT jwt) {
        mJwt = jwt;
    }


    public boolean isExpired() {
        return mJwt.isExpired(0);
    }

    public String getEmail() {
        if (!mJwt.isExpired(0)) {
            return mJwt.getClaim("email").asString();
        } else {
            throw new IllegalStateException("JWT is expired!");
        }
    }


    public static class UserInfoViewModelFactory implements ViewModelProvider.Factory {

        private final JWT jwt;

        public UserInfoViewModelFactory(JWT jwt) {
            this.jwt = jwt;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass == UserInfoViewModel.class) {
                return (T) new UserInfoViewModel(jwt);
            }
            throw new IllegalArgumentException(
                    "Argument must be: " + UserInfoViewModel.class);
        }
    }





}
