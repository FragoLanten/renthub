package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.Advertisement;
import ru.jdbcfighters.renthub.domain.models.Estate;

import java.util.List;

public interface AdvertisementService {

    Advertisement create(Advertisement advertisement);

    Advertisement get(Long id);

    List<Advertisement> getAll();

    Advertisement update(Advertisement advertisement);

    //Visible = false
    Advertisement delete(Long id);

    //Удаление из БД
    void hardDelete (Long id);

//    boolean checkIfIdExist(Long id);

    //Получить объявление по связанному объекту
    Advertisement getAdvertisementByEstate(Estate estate);

    //Повышает уровень объявления
    Advertisement rankUp(Advertisement advertisement, Integer rank);

    //Понижает уровень объявления
    Advertisement rankDown(Advertisement advertisement, Integer rank);

    //Начинает платное продвижение
    Advertisement startPromotion (Advertisement advertisement, Long amountOfDays, Integer rank);

}
