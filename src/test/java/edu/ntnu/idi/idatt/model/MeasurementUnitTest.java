package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Test class for the MeasurementUnit enum. */
@SuppressWarnings("DataFlowIssue")
class MeasurementUnitTest {

  /** Tests the fromString method with valid inputs. */
  @Test
  void fromStringValidInputs() {
    assertEquals(MeasurementUnit.LITER, MeasurementUnit.fromString("liter"));
    assertEquals(MeasurementUnit.LITER, MeasurementUnit.fromString("LITER"));
    assertEquals(MeasurementUnit.LITER, MeasurementUnit.fromString("Liters"));
    assertEquals(MeasurementUnit.LITER, MeasurementUnit.fromString("l"));
    assertEquals(MeasurementUnit.KILOGRAM, MeasurementUnit.fromString("kg"));
    assertEquals(MeasurementUnit.KILOGRAM, MeasurementUnit.fromString("Kilogram"));
    assertEquals(MeasurementUnit.KILOGRAM, MeasurementUnit.fromString("KILOGRAMS"));
    assertEquals(MeasurementUnit.KILOGRAM, MeasurementUnit.fromString("kilos"));
    assertEquals(MeasurementUnit.PCS, MeasurementUnit.fromString("pcs"));
    assertEquals(MeasurementUnit.PCS, MeasurementUnit.fromString("Pieces"));
    assertEquals(MeasurementUnit.PCS, MeasurementUnit.fromString("piece"));
    assertEquals(MeasurementUnit.PCS, MeasurementUnit.fromString("STK"));
  }

  /** Tests the fromString method with invalid inputs. */
  @Test
  void fromStringInvalidInputs() {
    assertThrows(IllegalArgumentException.class, () -> MeasurementUnit.fromString("krone"));
    assertThrows(IllegalArgumentException.class, () -> MeasurementUnit.fromString("meter"));
    assertThrows(IllegalArgumentException.class, () -> MeasurementUnit.fromString(""));
    assertThrows(NullPointerException.class, () -> MeasurementUnit.fromString(null));
  }
}
