public class Pair <T1,T2> {
    T1 key;
    T2 value;

    Pair(T1 key, T2 value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString(){
        return "<"+key.toString()+","+value.toString()+">";
    }
}
