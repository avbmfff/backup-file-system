package RepositorySystem;

import Strategy.StrategyImpl;
import lombok.Getter;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.Date;

@NonNull
@Getter
public class RestorePoint {
    private String name;
    private Date date;
    private ArrayList<Storage> storages;
    private StrategyImpl strategy;
}
