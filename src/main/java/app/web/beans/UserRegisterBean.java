package app.web.beans;

import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("register")
@RequestScoped
public class UserRegisterBean extends BaseBean {

    private UserRegisterBindingModel userRegister;

    private ModelMapper modelMapper;
    private UserService userService;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(ModelMapper modelMapper,
                            UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        this.userRegister = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(UserRegisterBindingModel userRegister) {
        this.userRegister = userRegister;
    }

    public void register() {
        if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) { return; }

        UserServiceModel user = modelMapper.map(userRegister, UserServiceModel.class);

        if (! userService.userRegister(user)) { return; }

        redirect("/login");
    }
}

