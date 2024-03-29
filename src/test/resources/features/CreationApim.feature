#language: en
Feature: Creation_User
  test  de creation d'un user Apim cas passant

  Scenario Outline: : cas passant de creation dun user apim
    Given je vais sur la page web "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And j'entre un username "<username>"
    And j'entre un passeword "<password>"
    And je clique sur le bouton login
    And je clique sur Apim
    And je clique sur le bouton Add
    And je renseigne un firstname "<firstname>"
    And je renseigne un middlename "<middlename>"
    And je renseigne un lastname "<lastname>"
    And je clique sur le bouton Create Login Details
    And je renseigne un UserNameApim "<user>"
    And je renseigne un psw "<pws>"
    And je renseigne un confirmpsw "<confirmpws>"
    When je click sur le bouton save
    Then la page Personal Details saffiche "<titre>"
    Examples:
      | username | password | firstname | middlename | lastname | titre            | user    | pws     | confirmpws |
      | Admin    | admin123 | test      | test       | test     | Personal Details | Nassima | user123 | user123    |
