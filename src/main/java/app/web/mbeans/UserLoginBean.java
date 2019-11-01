package app.web.mbeans;

import app.domain.models.binding.UserLoginBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("login")
@RequestScoped
public class UserLoginBean extends BaseBean {

    private UserLoginBindingModel userLogin;

    private ModelMapper modelMapper;
    private UserService userService;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        userLogin = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLoginBindingModel userLogin) {
        this.userLogin = userLogin;
    }

    public void login() {
        UserServiceModel userServiceModel = userService.userLogin(modelMapper.map(userLogin, UserServiceModel.class));

        if (userServiceModel == null) {
            redirect("/login");
            return;
        }


        session().setAttribute("username", userServiceModel.getUsername());
        session().setAttribute("role", userServiceModel.getRole());

        redirect("/home");
    }
}
