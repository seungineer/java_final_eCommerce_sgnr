# java_final_eCommerce_sgnr/tree/main


```mermaid
flowchart TD
    main[Main.java]:::entry

    subgraph Controllers
        adminCtrl[AdminProductController]:::controller
        loginCtrl[LoginController]:::controller
        signupCtrl[SignupController]:::controller
        userCtrl[UserController]:::controller
    end

    subgraph Views
        adminView[AdminMenuView]:::view
        loginView[LoginView]:::view
        detailView[ProductDetailView]:::view
        listView[ProductListView]:::view
        signupView[SignupView]:::view
        userView[UserMenuView]:::view
    end

    subgraph Models
        productModel[Product]:::model
        userModel[User]:::model
    end

    subgraph DAOs
        productDAO[ProductDAO]:::dao
        userDAO[UserDAO]:::dao
    end

    subgraph DTOs
        productInsertDto[ProductInsertDTO]:::dto
        loginRequestDto[UserLoginRequestDTO]:::dto
        loginResponseDto[UserLoginResponseDTO]:::dto
        userModifyDto[UserModifyDTO]:::dto
        userInsertDto[UserInsertDTO]:::dto
    end

    subgraph Utilities
        connMgr[ConnectionManager]:::util
        dateFmt[DateFormatter]:::util
        priceFmt[FormatPrice]:::util
        session[UserSession]:::util
        validator[Validator]:::util
    end

    subgraph Exceptions
        dataEx[DataAccessException]:::exception
        insertEx[InsertFailedException]:::exception
        modifyQtyEx[NotValidModifyQuantityCommandException]:::exception
        inputEx[NotValidProductInputException]:::exception
        pwEx[PasswordMismatchException]:::exception
        updateEx[UpdateFailedException]:::exception
    end

    db[(Database)]:::dao

    %% Entry point
    main --> adminCtrl
    main --> loginCtrl
    main --> signupCtrl
    main --> userCtrl

    %% Controller → View, Model, DAO
    adminCtrl --> adminView
    adminCtrl --> productModel
    adminCtrl --> productDAO
    loginCtrl --> loginView
    loginCtrl --> userModel
    loginCtrl --> userDAO
    signupCtrl --> signupView
    signupCtrl --> userModel
    signupCtrl --> userDAO
    userCtrl --> userView
    userCtrl --> userModel
    userCtrl --> userDAO

    %% Additional view links
    adminCtrl --> listView
    adminCtrl --> detailView

    %% Controller → Utilities
    adminCtrl --> validator
    loginCtrl --> validator
    signupCtrl --> validator
    userCtrl --> session

    %% DAO → Utilities
    productDAO --> connMgr
    userDAO --> connMgr

    %% DAO → DTOs
    productDAO --> productInsertDto
    userDAO --> loginRequestDto
    userDAO --> loginResponseDto
    userDAO --> userModifyDto
    userDAO --> userInsertDto

    %% DAO → DB
    productDAO --> db
    userDAO --> db

    %% Exception dotted lines
    adminCtrl -.-> dataEx
    loginCtrl -.-> pwEx
    signupCtrl -.-> insertEx
    userCtrl -.-> modifyQtyEx
    productDAO -.-> updateEx
    userDAO -.-> inputEx

    %% Styles
    classDef entry fill:#f9f,stroke:#333,stroke-width:2px;
    classDef controller fill:#bbf,stroke:#333,stroke-width:2px;
    classDef model fill:#cfc,stroke:#333,stroke-width:2px;
    classDef view fill:#fcf,stroke:#333,stroke-width:2px;
    classDef dao fill:#cff,stroke:#333,stroke-width:2px;
    classDef dto fill:#ccf,stroke:#333,stroke-width:2px;
    classDef util fill:#ffc,stroke:#333,stroke-width:2px;
    classDef exception fill:#fcc,stroke:#333,stroke-width:2px;

    %% Click Events
    click main "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/Main.java"
    click adminCtrl "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/controller/AdminProductController.java"
    click loginCtrl "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/controller/LoginController.java"
    click signupCtrl "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/controller/SignupController.java"
    click userCtrl "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/controller/UserController.java"
    click adminView "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/view/AdminMenuView.java"
    click loginView "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/view/LoginView.java"
    click detailView "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/view/ProductDetailView.java"
    click listView "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/view/ProductListView.java"
    click signupView "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/view/SignupView.java"
    click userView "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/view/UserMenuView.java"
    click productModel "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/model/Product.java"
    click userModel "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/model/User.java"
    click productDAO "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/dao/ProductDAO.java"
    click userDAO "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/dao/UserDAO.java"
    click productInsertDto "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/dto/ProductInsertDTO.java"
    click loginRequestDto "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/dto/UserLoginRequestDTO.java"
    click loginResponseDto "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/dto/UserLoginResponseDTO.java"
    click userModifyDto "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/dto/UserModifyDTO.java"
    click userInsertDto "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/dto/UserInsertDTO.java"
    click connMgr "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/util/ConnectionManager.java"
    click dateFmt "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/util/DateFormatter.java"
    click priceFmt "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/util/FormatPrice.java"
    click session "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/util/UserSession.java"
    click validator "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/util/Validator.java"
    click dataEx "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/exception/DataAccessException.java"
    click insertEx "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/exception/InsertFailedException.java"
    click modifyQtyEx "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/exception/NotValidModifyQuantityCommandException.java"
    click inputEx "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/exception/NotValidProductInputException.java"
    click pwEx "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/exception/PasswordMismatchException.java"
    click updateEx "github.com/seungineer/java_final_eCommerce_sgnr/tree/main/src/exception/UpdateFailedException.java"
```

- `Main.java`를 진입점으로 하여 사용자의 로그인, 회원가입, 상품 등록 및 조회와 같은 핵심 흐름을 컨트롤러 계층에서 분기한다.
- 각 컨트롤러는 `DTO`를 통해 사용자 입력을 전달받고 `DAO`를 통해 실제 데이터베이스에 접근하여 상품, 사용자 등 주요 모델을 조작한다.
- 유틸리티 클래스는 입력 검증과 가격/날짜 포맷을 담당하며, 예외 계층은 입력 오류나 DB 작업 실패를 분리 처리한다.
