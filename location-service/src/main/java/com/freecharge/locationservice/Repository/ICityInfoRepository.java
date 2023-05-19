package com.freecharge.locationservice.Repository;

import com.freecharge.locationservice.Entity.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICityInfoRepository extends JpaRepository<CityInfo,Integer> {

 //   @Query(value = "Select * from cityinfo where multi_polygon is null and Latitude not like ('0.00%') ",nativeQuery = true)
   // List<CityInfo> findByCity();

    @Query(value = "SELECT * FROM geopincode.cityinfo where pincode in (\"143114\",\"143119\",\"143149\",\"143413\",\"143517\",\"144024\",\"144030\",\"144032\",\"144106\",\"144202\",\"144302\",\"144311\",\"144409\",\"144501\",\"144603\",\"144620\",\"144630\",\"144805\",\"146110\",\"147006\",\"147007\",\"148002\",\"259355\",\"272170\",\"301002\",\"302040\",\"303501\",\"305009\",\"305012\",\"305021\",\"307031\",\"311001\",\"311026\",\"312025\",\"312612\",\"312622\",\"313207\",\"313323\",\"314023\",\"314027\",\"314035\",\"314402\",\"321202\",\"321301\",\"321405\",\"321411\",\"321613\",\"322234\",\"322249\",\"323303\",\"323305\",\"326529\",\"328029\",\"331501\",\"333701\",\"334006\",\"334601\",\"335021\",\"335061\",\"335064\",\"335702\",\"341511\",\"342304\",\"343002\",\"343023\",\"343049\",\"360020\",\"360055\",\"360060\",\"360070\",\"360320\",\"360330\",\"360421\",\"360440\",\"360460\",\"360470\",\"360515\",\"361013\",\"361141\",\"361142\",\"361150\",\"361330\",\"361347\",\"362004\",\"362235\",\"362315\",\"362725\",\"363351\",\"363415\",\"363630\",\"363660\",\"364005\",\"364006\",\"364050\",\"364070\",\"364081\",\"364135\",\"364150\",\"364250\",\"364313\",\"364470\",\"364485\",\"364490\",\"364530\",\"364730\",\"364760\",\"365460\",\"365555\",\"365565\",\"382045\",\"382145\",\"382170\",\"382245\",\"382250\",\"382732\",\"382860\",\"383006\",\"383010\",\"383210\",\"383316\",\"383317\",\"383330\",\"383434\",\"383450\",\"384003\",\"384012\",\"384215\",\"384241\",\"384272\",\"384360\",\"385506\",\"387115\",\"387230\",\"387305\",\"387310\",\"387325\",\"387330\",\"387355\",\"387380\",\"387570\",\"388130\",\"388140\",\"388307\",\"388330\",\"388345\",\"388440\",\"388460\",\"388543\",\"388545\",\"388550\",\"388570\",\"388590\",\"388630\",\"389002\",\"389235\",\"389250\",\"389320\",\"389370\",\"391244\",\"391345\",\"391346\",\"391445\",\"391745\",\"391750\",\"391761\",\"391774\",\"392030\",\"392035\",\"392135\",\"392160\",\"392215\",\"392310\",\"393017\",\"393105\",\"394345\",\"394352\",\"394517\",\"394530\",\"394641\",\"395013\",\"396035\",\"396440\",\"396570\",\"403101\",\"403109\",\"403114\",\"403201\",\"403206\",\"403409\",\"403526\",\"403530\",\"403717\",\"403803\",\"450051\",\"450110\",\"450119\",\"450337\",\"452013\",\"453112\",\"453332\",\"454335\",\"454774\",\"456441\",\"457336\",\"457772\",\"458778\",\"461122\",\"462004\",\"462027\",\"462031\",\"462033\",\"462101\",\"464113\",\"464240\",\"464671\",\"464672\",\"465227\",\"465335\",\"466113\",\"466118\",\"466120\",\"470021\",\"470051\",\"470124\",\"470228\",\"470232\",\"470669\",\"472101\",\"472337\",\"472446\",\"473112\",\"473113\",\"473444\",\"473670\",\"473793\",\"474015\",\"475685\",\"476134\",\"476219\",\"476335\",\"476554\",\"477117\",\"477446\",\"477449\",\"477555\",\"477566\",\"480003\",\"480112\",\"480553\",\"480880\",\"480991\",\"482008\",\"482009\",\"482021\",\"483053\",\"483773\",\"484120\",\"484664\",\"485113\",\"485114\",\"485334\",\"485441\",\"485772\",\"486005\",\"486006\",\"486440\",\"486445\",\"486447\",\"486450\",\"486451\",\"486675\",\"486776\",\"486884\",\"486888\",\"487334\",\"487337\",\"488059\",\"488222\",\"507113\",\"507134\",\"600004\",\"600005\",\"600006\",\"600007\",\"600008\",\"600009\",\"600011\",\"600012\",\"600021\",\"600022\",\"600024\",\"600025\",\"600026\",\"600030\",\"600031\",\"600034\",\"600035\",\"600036\",\"600049\",\"600052\",\"600060\",\"600066\",\"600067\",\"600068\",\"600076\",\"600077\",\"600083\",\"600084\",\"600085\",\"600086\",\"600098\",\"600099\",\"600101\",\"600102\",\"600115\",\"600122\",\"600127\",\"600128\",\"600129\",\"600130\",\"601201\",\"601203\",\"602117\",\"603001\",\"603101\",\"603106\",\"603201\",\"603303\",\"603304\",\"603309\",\"603310\",\"603311\",\"603314\",\"603319\",\"603402\",\"603403\",\"603405\",\"604201\",\"604203\",\"604210\",\"604303\",\"604306\",\"604403\",\"604404\",\"604501\",\"604502\",\"604503\",\"604504\",\"604601\",\"605402\",\"605701\",\"605702\",\"605752\",\"605755\",\"605756\",\"605757\",\"605758\",\"605759\",\"605802\",\"605803\",\"606105\",\"606111\",\"606203\",\"606204\",\"606205\",\"606208\",\"606303\",\"606604\",\"606611\",\"606701\",\"606703\",\"606705\",\"606706\",\"606707\",\"606708\",\"606709\",\"606754\",\"606805\",\"606806\",\"606807\",\"606901\",\"606903\",\"607102\",\"607103\",\"607104\",\"607105\",\"607107\",\"607108\",\"607109\",\"607112\",\"607203\",\"607209\",\"607308\",\"607403\",\"607801\",\"607802\",\"607803\",\"607804\",\"607805\",\"607807\",\"608001\",\"608201\",\"608301\",\"608303\",\"608304\",\"608305\",\"608306\",\"608401\",\"608901\",\"609001\",\"609003\",\"609103\",\"609105\",\"609107\",\"609201\",\"609204\",\"609205\",\"609301\",\"609302\",\"609303\",\"609402\",\"609504\",\"609811\",\"610102\",\"610103\",\"610104\",\"610105\",\"610106\",\"610201\",\"610202\",\"612104\",\"612106\",\"612302\",\"612303\",\"612503\",\"612603\",\"612610\",\"612702\",\"612703\",\"612801\",\"612803\",\"612904\",\"613001\",\"613002\",\"613003\",\"613004\",\"613005\",\"613101\",\"613102\",\"613103\",\"613202\",\"613203\",\"613204\",\"613205\",\"613301\",\"613303\",\"613401\",\"613403\",\"613503\",\"613704\",\"613705\",\"614017\",\"614018\",\"614103\",\"614201\",\"614202\",\"614206\",\"614207\",\"614208\",\"614210\",\"614211\",\"614601\",\"614704\",\"614710\",\"614715\",\"614716\",\"614805\",\"614806\",\"614809\",\"620011\",\"621108\",\"621109\",\"621110\",\"621111\",\"621112\",\"621113\",\"621115\",\"621117\",\"621118\",\"621133\",\"621202\",\"621203\",\"621204\",\"621205\",\"621206\",\"621208\",\"621210\",\"621211\",\"621214\",\"621311\",\"621312\",\"621651\",\"621652\",\"621653\",\"621701\",\"621702\",\"621709\",\"621713\",\"621715\",\"621717\",\"621718\",\"621803\",\"621806\",\"622003\",\"622004\",\"622005\",\"622203\",\"622403\",\"622404\",\"622407\",\"622409\",\"622411\",\"622412\",\"622422\",\"622507\",\"623135\",\"623308\",\"623315\",\"623401\",\"623402\",\"623407\",\"623514\",\"623519\",\"623520\",\"623521\",\"623522\",\"623529\",\"623530\",\"623534\",\"623536\",\"623537\",\"623538\",\"623701\",\"624001\",\"624002\",\"624003\",\"624004\",\"624005\",\"624206\",\"624208\",\"624211\",\"624212\",\"624215\",\"624220\",\"624301\",\"624302\",\"624303\",\"624304\",\"624308\",\"624401\",\"624703\",\"624704\",\"624708\",\"625001\",\"625004\",\"625005\",\"625006\",\"625007\",\"625008\",\"625014\",\"625015\",\"625016\",\"625019\",\"625103\",\"625104\",\"625207\",\"625214\",\"625234\",\"625513\",\"625514\",\"625515\",\"625516\",\"625517\",\"625520\",\"625522\",\"625523\",\"625529\",\"625530\",\"625531\",\"625533\",\"625534\",\"625540\",\"625556\",\"625705\",\"625707\",\"625708\",\"626001\",\"626005\",\"626102\",\"626106\",\"626107\",\"626108\",\"626119\",\"626124\",\"626125\",\"626128\",\"626140\",\"626141\",\"626201\",\"627012\",\"627101\",\"627102\",\"627103\",\"627114\",\"627118\",\"627119\",\"627120\",\"627127\",\"627131\",\"627133\",\"627351\",\"627359\",\"627401\",\"627412\",\"627413\",\"627416\",\"627417\",\"627421\",\"627422\",\"627423\",\"627427\",\"627453\",\"627604\",\"627659\",\"628004\",\"628005\",\"628215\",\"628216\",\"628217\",\"628218\",\"628219\",\"628702\",\"628703\",\"628721\",\"628752\",\"628753\",\"628801\",\"628802\",\"628809\",\"629165\",\"629166\",\"629167\",\"629169\",\"629170\",\"629171\",\"629172\",\"629179\",\"629180\",\"629193\",\"629201\",\"629252\",\"629702\",\"630005\",\"630313\",\"630314\",\"630321\",\"630405\",\"630408\",\"630410\",\"630411\",\"630501\",\"630502\",\"630553\",\"631002\",\"631003\",\"631004\",\"631005\",\"631006\",\"631051\",\"631052\",\"631101\",\"631151\",\"631152\",\"631201\",\"631205\",\"631208\",\"631210\",\"631211\",\"631212\",\"631213\",\"631301\",\"631304\",\"631402\",\"631606\",\"631702\",\"632001\",\"632010\",\"632011\",\"632012\",\"632013\",\"632014\",\"632055\",\"632057\",\"632058\",\"632101\",\"632102\",\"632113\",\"632114\",\"632115\",\"632201\",\"632209\",\"632301\",\"632311\",\"632312\",\"632401\",\"632403\",\"632405\",\"632406\",\"632502\",\"632505\",\"632508\",\"632521\",\"632531\",\"632602\",\"632603\",\"632604\",\"635703\",\"635710\",\"635751\",\"635752\",\"635754\",\"635805\",\"635806\",\"635807\",\"635808\",\"635810\",\"635811\",\"635812\",\"635813\",\"635814\",\"635815\",\"635851\",\"635852\",\"636004\",\"636005\",\"636006\",\"636007\",\"636010\",\"636011\",\"636012\",\"636013\",\"636014\",\"636015\",\"636016\",\"636030\",\"636101\",\"636102\",\"636104\",\"636107\",\"636109\",\"636111\",\"636113\",\"636121\",\"636138\",\"636141\",\"636142\",\"636303\",\"636308\",\"636309\",\"636351\",\"636401\",\"636406\",\"636454\",\"636455\",\"636456\",\"636457\",\"636458\",\"637204\",\"637208\",\"637210\",\"637211\",\"637403\",\"637404\",\"637405\",\"637406\",\"637408\",\"637409\",\"637410\",\"637504\",\"637505\",\"638001\",\"638002\",\"638003\",\"638004\",\"638005\",\"638006\",\"638007\",\"638456\",\"638457\",\"638462\",\"638476\",\"639006\",\"639107\",\"639109\",\"639110\",\"639117\",\"639201\",\"639205\",\"641002\",\"641003\",\"641008\",\"641009\",\"641011\",\"641026\",\"641028\",\"641029\",\"641030\",\"641031\",\"641032\",\"641033\",\"641035\",\"641038\",\"641039\",\"641044\",\"641045\",\"641046\",\"641047\",\"641048\",\"641049\",\"641050\",\"641062\",\"641101\",\"641103\",\"641104\",\"641105\",\"641107\",\"641108\",\"641109\",\"641110\",\"641111\",\"641112\",\"641113\",\"641114\",\"641201\",\"641202\",\"641301\",\"641302\",\"641305\",\"641401\",\"641402\",\"641407\",\"641601\",\"641602\",\"641603\",\"641604\",\"641605\",\"641606\",\"641607\",\"641652\",\"641653\",\"641654\",\"641655\",\"641658\",\"641659\",\"641662\",\"641663\",\"641664\",\"641665\",\"641666\",\"641667\",\"641668\",\"641669\",\"641670\",\"641671\",\"641687\",\"641697\",\"642001\",\"642002\",\"642003\",\"642004\",\"642005\",\"642007\",\"642101\",\"642102\",\"642103\",\"642104\",\"642105\",\"642106\",\"642107\",\"642108\",\"642109\",\"642110\",\"642111\",\"642112\",\"642113\",\"642114\",\"642120\",\"642122\",\"642123\",\"642125\",\"642126\",\"642127\",\"642128\",\"642129\",\"642130\",\"642132\",\"642133\",\"642134\",\"642154\",\"642201\",\"642202\",\"642203\",\"642204\",\"642205\",\"642206\",\"642207\",\"643001\",\"643002\",\"643003\",\"643004\",\"643005\",\"643006\",\"643007\",\"643101\",\"643102\",\"643103\",\"643105\",\"643201\",\"643202\",\"643203\",\"643204\",\"643205\",\"643206\",\"643207\",\"643209\",\"643211\",\"643212\",\"643213\",\"643214\",\"643215\",\"643217\",\"643218\",\"643219\",\"643220\",\"643221\",\"643223\",\"643224\",\"643225\",\"643226\",\"643228\",\"643231\",\"643233\",\"643236\",\"643237\",\"643238\",\"643239\",\"643240\",\"643241\",\"643242\",\"643243\",\"643253\",\"682551\",\"682554\",\"731213\",\"744104\",\"744106\",\"744112\",\"744201\",\"744203\",\"744205\",\"744301\",\"744302\",\"744303\",\"744304\",\"751004\",\"751008\",\"751011\",\"752003\",\"752013\",\"752016\",\"752037\",\"752045\",\"752083\",\"752091\",\"752103\",\"752105\",\"752107\",\"752115\",\"752119\",\"752120\",\"752121\",\"753006\",\"753015\",\"754002\",\"754012\",\"754013\",\"754018\",\"754024\",\"754035\",\"754120\",\"754131\",\"754135\",\"754138\",\"754143\",\"754145\",\"754162\",\"754203\",\"754209\",\"754239\",\"754290\",\"754294\",\"754295\",\"755003\",\"755025\",\"755049\",\"756055\",\"756059\",\"756083\",\"756085\",\"756118\",\"756119\",\"756128\",\"756144\",\"756164\",\"756165\",\"756166\",\"756167\",\"756168\",\"756171\",\"756181\",\"756182\",\"757001\",\"757002\",\"757003\",\"757014\",\"757016\",\"757017\",\"757018\",\"757019\",\"757020\",\"757021\",\"757022\",\"757023\",\"757024\",\"757025\",\"757026\",\"757027\",\"757028\",\"757029\",\"757030\",\"757031\",\"757032\",\"757033\",\"757034\",\"757035\",\"757036\",\"757037\",\"757038\",\"757039\",\"757040\",\"757041\",\"757042\",\"757043\",\"757045\",\"757046\",\"757047\",\"757048\",\"757049\",\"757050\",\"757051\",\"757052\",\"757053\",\"757054\",\"757055\",\"757073\",\"757074\",\"757075\",\"757077\",\"757079\",\"757081\",\"757082\",\"757083\",\"757084\",\"757085\",\"757086\",\"757087\",\"757091\",\"757092\",\"757093\",\"757100\",\"757101\",\"757102\",\"757103\",\"757104\",\"757105\",\"757106\",\"757107\",\"758001\",\"758002\",\"758013\",\"758014\",\"758015\",\"758016\",\"758017\",\"758018\",\"758019\",\"758020\",\"758021\",\"758022\",\"758023\",\"758025\",\"758026\",\"758027\",\"758028\",\"758029\",\"758030\",\"758031\",\"758032\",\"758034\",\"758035\",\"758036\",\"758037\",\"758038\",\"758040\",\"758041\",\"758043\",\"758044\",\"758045\",\"758046\",\"758047\",\"758076\",\"758078\",\"758079\",\"758080\",\"758081\",\"758082\",\"758083\",\"758084\",\"758085\",\"759001\",\"759013\",\"759014\",\"759015\",\"759016\",\"759017\",\"759018\",\"759019\",\"759020\",\"759021\",\"759022\",\"759023\",\"759024\",\"759025\",\"759026\",\"759027\",\"759028\",\"759037\",\"759039\",\"759040\",\"759111\",\"759147\",\"759148\",\"761031\",\"761114\",\"761115\",\"761116\",\"761120\",\"761133\",\"761208\",\"763001\",\"763004\",\"764006\",\"764011\",\"764062\",\"764088\",\"765023\",\"765033\",\"766026\",\"766032\",\"767042\",\"768052\",\"768233\",\"769009\",\"769016\",\"770070\",\"770073\",\"797005\",\"835324\") and pincode not in ('607403','480112','480003','395013','259355','392135','389002','364470','323305','323303','148002','144603','147006','147007')",nativeQuery = true)
    List<CityInfo> findByCity();

    @Query(value = "SELECT *  FROM geopincode.cityinfo where multi_polygon is not null  and State not in ('ANDHRA PRADESH','ARUNACHAL PRADESH','ASSAM','BIHAR','CHHATTISGARH','HARYANA','HIMACHAL PRADESH','JAMMU & KASHMIR','JHARKHAND','KARNATAKA','KERALA','MAHARASHTRA','MANIPUR','MEGHALAYA','MIZORAM','NAGALAND','TAMIL NADU','TELANGANA','TRIPURA','UTTAR PRADESH','UTTARAKHAND','WEST BENGAL','ODISHA')",nativeQuery = true)
    List<CityInfo> findAllCityInfo();

    @Query(value = "Select * from geopincode.cityinfo where Pincode not in (Select pincode from geopincode.pincode_polygon_details ) and multi_polygon is not null",nativeQuery = true)
    List<CityInfo> findByPincode();

}
