package bsu.java.group6.lab3.Ivleva.var1;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
	private Double[] coefficients;
	private Double from;
	private Double to;
	private Double step;
	public GornerTableModel(Double from, Double to, Double step,
			Double[] coefficients) {
		this.from = from;
		this.to = to;
		this.step = step;
		this.coefficients = coefficients;
	}
	public Double getFrom() {
		return from;
	}
	public Double getTo() {
		return to;
	}
	public Double getStep() {
		return step;
	}
	public int getColumnCount() {
		// В данной модели два столбца
		return 3;
	}
	public int getRowCount() {
		// Вычислить количество точек между началом и концом отрезка
		// исходя из шага табулирования
		return new Double(Math.ceil((to-from)/step)).intValue()+1;
	}
	public Object getValueAt(int row, int col) {
		// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
		double x = from + step*row;
		double result;
		
		if (col==0) {
			// Если запрашивается значение 1-го столбца, то это X
			return x;
		} else if(col==1){
			// Если запрашивается значение 2-го столбца, то это значение
			// многочлена
			result = coefficients[0];
			for(int i=1;i< coefficients.length;i++) {
				result=result*x+coefficients[i];
			}
			return result;
		} else {
			result = coefficients[0];
			for(int i=1;i< coefficients.length;i++) {
				result=result*x+coefficients[i];
			}
			return result>0;
		}

	}
	
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			// Название 1-го столбца
			return "Значение X";
		case 1:
			// Название 2-го столбца
			return "Значение многочлена";
		case 2:
			return "Больше нуля?";
		default:
			return "";
		}
	}
	public Class<?> getColumnClass(int col) {
		if(col==2) {
			return Boolean.class;
		}
		// И в 1-ом и во 2-ом столбце находятся значения типа Double
		return Double.class;
	}
}