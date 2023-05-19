package com.freecharge.locationservice.Service.ServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freecharge.locationservice.Dto.Response.PincodeResponse;
import com.freecharge.locationservice.Entity.*;
import com.freecharge.locationservice.Repository.*;
import com.freecharge.locationservice.Service.ILocationService;
import com.freecharge.locationservice.Utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class LocationServiceImpl implements ILocationService {


    @Autowired
    ILocationRepository repository;

    @Autowired
    ILatLongRepository latLongRepository;

    @Autowired
    IResultRepository resultRepository;
    List<PincodePolygonDetails> allData = new ArrayList<>();
    List<Result> allResult = new ArrayList<>();
    List<String> collect = new ArrayList<>();
   @PostConstruct
   void init(){
       allData= repository.findlatLongNotNull();
       allResult = resultRepository.findAll();
        collect = allResult.stream().map(Result::getPincodeFromOld).collect(Collectors.toList());
        log.info("{}",collect);
   }

    @Override
    public List<Root> setDataToDb(String stateName) {
        List<Root> rootList = new ArrayList<>();
        String folderPath = "C:/Users/vishal.manik/locationgeojson/India_Pincode_Boundary_Data/" + stateName + ".geojson";
        try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
            paths.filter(Files::isRegularFile).forEach(path -> {
                File file = new File(path.toString());
                Root root = null;
                try {
                    root = new ObjectMapper().readValue(Files.readAllBytes(path), Root.class);
                    //  log.info("Root Value {}",root);
                    parseDataForDB(root);
                    rootList.add(root);
                } catch (IOException e) {
                    log.error("Exception Occured :{}", root);
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            log.error("Exception occured outside {}", e.getMessage());
            e.printStackTrace();
        }

        return rootList;
    }

    private void parseDataForDB(Root root) {
        root.getFeatures().stream().forEach(geo -> {
            PincodePolygonDetails polygonDetails = new PincodePolygonDetails();
           // polygonDetails.setPincode(geo.getProperties().getPincode());
            //polygonDetails.setDistrict(geo.getProperties().getDistrict());
          //  polygonDetails.setState(geo.getProperties().getState());
           // polygonDetails.setName(geo.getProperties().getOfficename());
            log.info("{}", geo);
            polygonDetails.setMulti_polygon(parseToMultiPolygon(geo.getGeometry(), geo));
            PincodePolygonDetails save = repository.save(polygonDetails);

        });
    }

    private MultiPolygon parseToMultiPolygon(Geometery geo, Feature feature) {
        try {
            String s = JsonUtil.writeValueAsString(geo);
            // MultiPolygonWrapper map = MapperUtil.map(s, MultiPolygonWrapper.class);
            //MultiPolygon multiPolygon = map.getMultiPolygon();
            //MultiPolygon map = MapperUtil.map(s, MultiPolygon.class);
            //  log.info("{}",multiPolygon);
            return convert(s);
        } catch (Exception e) {
            log.error("Error Occured {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static MultiPolygon convert(String geoJson) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(geoJson);
        GeoJsonReader reader = new GeoJsonReader();
        Geometry geometry = reader.read(rootNode.toString());
        return createMultiPolygon(geometry);
    }

    private static MultiPolygon createMultiPolygon(Geometry geometry) {
        Polygon[] polygons;
        if (geometry instanceof MultiPolygon) {
            MultiPolygon multiPolygon = (MultiPolygon) geometry;
            polygons = new Polygon[multiPolygon.getNumGeometries()];
            for (int i = 0; i < multiPolygon.getNumGeometries(); i++) {
                polygons[i] = (Polygon) multiPolygon.getGeometryN(i);
            }
        } else {
            polygons = new Polygon[1];
            polygons[0] = (Polygon) geometry;
        }
        return new MultiPolygon(polygons, geometry.getFactory());
    }


    @Override
    public PincodeResponse findLatAndLong(Double longitude, Double latitude) {
        GeometryFactory factory = new GeometryFactory();

        String point ="POINT("+latitude+" "+longitude+")";
        log.info("{}",point);
        PincodePolygonDetails polygonContaining = repository.findPincodePolygonDetails(point);
        log.info("{}",polygonContaining);
        return parsePincodePolygonDetailsToResponse(polygonContaining);
    }


    private PincodeResponse parsePincodePolygonDetailsToResponse(PincodePolygonDetails polygonContaining) {
        if(Objects.nonNull(polygonContaining)){
           PincodeResponse response= new PincodeResponse();
           response.setPincode(polygonContaining.getPincode());
           response.setDistrict(polygonContaining.getDistrict());
           response.setState(polygonContaining.getState());
           response.setName(polygonContaining.getName());
            return response;
        }
        else {
            throw new RuntimeException("Not Valid lat and long");
        }
    }

    @Autowired
    ICityInfoRepository repositoryCity;
    @Override
    public List<String> mapPincode() {
        List<String> listPincode = new ArrayList<>();
        List<CityInfo> latLongs = repositoryCity.findByCity();
        latLongs.parallelStream().forEach(data->{
            String point = "POINT("+data.getLatitude()+" "+data.getLongitude()+")";
            PincodePolygonDetails pincodePolygonDetails = repository.findPincodePolygonDetails(point);
            new Thread(()->{
                if(Objects.nonNull(pincodePolygonDetails) && Objects.isNull(data.getMultiPolygon())){
                    data.setMultiPolygon(pincodePolygonDetails.getMulti_polygon());
                    repositoryCity.save(data);
                    listPincode.add(String.valueOf(data.getPincode()));
                }
            }).start();
        });

        return listPincode;
    }

    @Override
    public List<String> addToTable() {
        List<String> pinCode = new ArrayList<String>();
        List<CityInfo> allCityInfo = repositoryCity.findByPincode();
        allCityInfo.parallelStream().forEach(data -> {
            Optional<PincodePolygonDetails> byId = repository.findById(String.valueOf(data.getPincode()));
            if (!byId.isPresent()) {
                new Thread(() -> {
                    PincodePolygonDetails polygonDetails = new PincodePolygonDetails();
                    polygonDetails.setPincode(String.valueOf(data.getPincode()));
                    polygonDetails.setDistrict(data.getDistrict());
                    polygonDetails.setMulti_polygon(data.getMultiPolygon());
                    polygonDetails.setName(data.getCityName());
                    polygonDetails.setState(data.getState());
                    repository.save(polygonDetails);
                    pinCode.add(polygonDetails.getPincode());
                    log.info("{}", polygonDetails.getPincode());
                }).start();
            }
        });
        log.info("{}",pinCode.size());
        return pinCode;
    }

    @Override
    public void findLatLong(){
        RestTemplate template = new RestTemplate();
        String base="https://nominatim.openstreetmap.org/search.php";
        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("accept","*/*");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("accept","*/*");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        String[] pincode = {"143114","143119","143149","143413","143517","144024","144030","144032","144106","144202","144302","144311","144409","144501","144603","144620","144630","144805","146110","147006","147007","148002","259355","301002","302040","303501","305009","305012","305021","307031","311001","311026","312025","312612","312622","313207","313323","314023","314027","314035","314402","321202","321301","321405","321411","321613","322234","322249","323303","323305","326529","328029","331501","333701","334006","334601","335021","335061","335064","335702","341511","342304","343002","343023","343049","360020","360055","360060","360070","360320","360330","360421","360440","360460","360470","360515","361013","361141","361142","361150","361330","361347","362004","362235","362315","362725","363351","363415","363630","363660","364005","364006","364050","364070","364081","364135","364150","364250","364313","364470","364485","364490","364530","364730","364760","365460","365555","365565","382045","382145","382170","382245","382250","382732","382860","383006","383010","383210","383316","383317","383330","383434","383450","384003","384012","384215","384241","384272","384360","385506","387115","387230","387305","387310","387325","387330","387355","387380","387570","388130","388140","388307","388330","388345","388440","388460","388543","388545","388550","388570","388590","388630","389002","389235","389250","389320","389370","391244","391345","391346","391445","391745","391750","391761","391774","392030","392035","392135","392160","392215","392310","393017","393105","394345","394352","394517","394530","394641","395013","396035","396440","396570","403109","403201","403206","403409","403530","450051","450110","450119","450337","452013","453112","453332","454335","454774","456441","457336","457772","458778","461122","462004","462027","462031","462033","462101","464113","464240","464671","464672","465227","465335","466113","466118","466120","470021","470051","470124","470228","470232","470669","472101","472337","472446","473112","473113","473444","473670","473793","474015","475685","476134","476219","476335","476554","477117","477446","477449","477555","477566","480003","480112","480553","480880","480991","482008","482009","482021","483053","483773","484120","484664","485113","485114","485334","485441","485772","486005","486006","486440","486445","486447","486450","486451","486675","486776","486884","486888","487334","487337","488059","488222","490022","493195","493196","493229","493331","493448","493887","493888","493891","494347","494450","495003","495223","495448","495455","495555","495686","496108","496109","497117","607403","682551","682554","744106","744112","744201","744203","744205","744301","744302","744303","744304","782128","794003"};
        List<String> pincodeList = new ArrayList<String>();
        pincodeList = Arrays.asList(pincode);
        pincodeList.parallelStream().forEach(data->{
        String completeUrl = UriComponentsBuilder.fromHttpUrl(base).queryParam("q", data+"_India").queryParam("polygon_geojson", "1").queryParam("format", "json").toUriString();
            ResponseEntity<Object> exchange = template.exchange(completeUrl, HttpMethod.GET, httpEntity, Object.class);
            String str=null;
          if(  exchange.getStatusCode().is2xxSuccessful()){
              ObjectMapper mapper = new ObjectMapper();
              try {
                   str = mapper.writeValueAsString(exchange.getBody());
              } catch (JsonProcessingException e) {
                  throw new RuntimeException(e);
              }
          }
          log.info("{}----{}",data,str);
        });
    }

    @Autowired
    ILatLongPincodeRepository repositoryLatLong;
    @Override
    public List<String> parseJsonToDb() {
        try {
            List<String> responseList = new ArrayList<String>();
            String str = new String(Files.readAllBytes(Paths.get("C:\\Project\\indiaPincodeLocator\\data.json")));
            log.info("{}", str);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(str, Map.class);
            log.info("{}", map);
           log.info("Value{}",mapper.writeValueAsString(map.get("504218")));
            map.entrySet().parallelStream().forEach(data -> {
                try {
                    LatLongEntity latLongEntity = mapper.readValue(mapper.writeValueAsString(data.getValue()), LatLongEntity.class);
                    log.info("Entity {}", latLongEntity);
                    repositoryLatLong.save(latLongEntity);
                    responseList.add(latLongEntity.getPostal_code());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });
            return responseList;
        } catch (IOException e) {
            log.info("Exception {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
   /* @Override
    public List<String> addToTable() {
        List<String> pincode = new ArrayList<String>();
        List<LatLongEntity> all = repositoryLatLong.findAll();
        all.parallelStream().forEach(data->{
            String point ="POINT("+data.getLatitude()+" "+data.getLongitude()+")";
            log.info("{}",point);
            PincodePolygonDetails polygonContaining = repository.findPincodePolygonDetails(point);
            if(Objects.nonNull(polygonContaining)){
                log.info("{}",polygonContaining);
                data.setMulti_polygon(polygonContaining.getMulti_polygon());
                repositoryLatLong.save(data);
                pincode.add(data.getPostal_code());
            }
        });
    return pincode;
    }*/

    @Autowired
    IIndiaRepository indiaRepository;
    public String mapLatLong(){
        List<String> pincode = new ArrayList<>();
        List<PincodePolygonDetails> all = repository.findAll();
        all.parallelStream().forEach(data->{
            List<India> indiaByPostal_code = indiaRepository.findIndiasByPostalCode(data.getPincode());
            if(!CollectionUtils.isEmpty(indiaByPostal_code)){
                India india = indiaByPostal_code.get(0);
                data.setLatitude(india.getLatitude());
                data.setLongitude(india.getLongitude());
                repository.save(data);
                log.info("Pincode Updated {}",data.getPincode());
                pincode.add(data.getPincode());
            }
        });
        log.info("pincode {}",pincode);
        return String.valueOf(pincode.size());
    }


    public  void makeResult(){
        List<String> unsuccessfulList = new ArrayList<>();
        try{
    if(CollectionUtils.isEmpty(allData)){
        allData =repository.findAll();
    }
    allData.parallelStream().forEach(data->{
        if(!collect.contains(data.getPincode())) {
            try{
            String point = "POINT(" + data.getLatitude() + " " + data.getLongitude() + " )";
            PincodePolygonDetails pincodePolygonDetails = repository.findPincodePolygonDetails(point);
            List<India> indiasByPostalCode = indiaRepository.findIndiasByPostalCode(pincodePolygonDetails.getPincode());
            List<String> postalCodeList = indiasByPostalCode.stream().map(India::getPostalCode).collect(Collectors.toList());
            List<String> stateList = indiasByPostalCode.stream().map(India::getState).collect(Collectors.toList());
                Result result = new Result();
                result.setPincodeFromOld(data.getPincode());
                result.setPincodeFromNew(postalCodeList.get(0));
                if (postalCodeList.contains(data.getPincode()) ) {
                    result.setResult("SUCCESS");
                    resultRepository.save(result);
                } else if (!postalCodeList.contains(data.getPincode())) {
                    result.setResult("Data Mismatch lat" + data.getLatitude() + "/long" + data.getLongitude()+"///"+data.getState());
                    resultRepository.save(result);
                } else if (stateList.contains(data.getState())) {
                    result.setResult("Pincode Mismatch lat" + data.getLatitude() + "/long" + data.getLongitude());
                    resultRepository.save(result);
                }

        }
        catch(Exception e){
                unsuccessfulList.add(data.getPincode());
                log.error("pincode {}",data.getPincode());

        }}
    });
        }catch (Exception e){

        }
    log.info("Data Completed{}",unsuccessfulList);

    }
}







